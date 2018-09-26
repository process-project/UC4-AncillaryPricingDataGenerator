package com.lhsystems.module.datageneratorancillary.service.sqlite.write;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Offers connection to sqlite database. Allows writing flight to said database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class SqliteWriter {

    /** The pattern for the end of the last value in an insert query. */
    private static final String END_OF_LAST_VALUE_SUBPATTERN = ")";

    /** The pattern for the end of an value in an insert query. */
    private static final String END_OF_VALUE_SUBPATTERN = "),\n";

    /** The pattern for a nonstring value in an insert query. */
    private static final String INSERT_NONSTRING_INTO_SUBPATTERN = "{0},";

    /** The pattern for the start of a row in an insert query. */
    private static final String START_QUERY_WITH_NONSTRING_SUBPATTERN = "({0},";
    /**
     * Pattern for adding unformatted flight attributes to a insert query.
     */
    private static final String INSERT_STRING_INTO_SUBPATTERN = "\"{0}\",";

    /**
     * Pattern for a SQLite query that inserts Flights into the database.
     */
    private static final String SQL_INSERT = "INSERT INTO {0} values {1};";

    /**
     * A connection with a specific database. SQL statements are executed and
     * results are returned within the context of this connection.
     */
    private final Connection connection;

    /**
     * Used for executing a SQL statement and returning the results.
     */
    private final Statement statement;

    /**
     * Constructor. connects to sqlite dataBase specified with
     * <code>dbPath</code>.
     *
     * @param paramConnection
     *            Connection to the SQLite database
     * @throws SQLException
     *             if no statement can be created.
     */
    public SqliteWriter(final Connection paramConnection) throws SQLException {
        connection = paramConnection;
        statement = paramConnection.createStatement();
    }

    /**
     * Write baggage classes of products in the respective relation table.
     *
     * @param products
     *            the products
     * @param maxId
     *            an upper bound for ids in the table
     *
     * @throws SQLException
     *             if the execution of the insert query fails
     */
    public void writeBaggageClassesOfProducts(
            final List<Product> products, final long maxId)
                    throws SQLException {
        long tempMaxId = maxId;
        final List<Product> copiedProducts = new ArrayList<>(
                products);
        final StringBuilder queryBuilder = new StringBuilder();
        final Product lastProduct = copiedProducts.remove(
                copiedProducts.size() - 1);
        for (final Product product : copiedProducts) {
            tempMaxId = appendBaggageClasses(
                    queryBuilder,
                    tempMaxId,
                    product,
                    false);
        }
        tempMaxId = appendBaggageClasses(
                queryBuilder,
                tempMaxId,
                lastProduct,
                true);
        statement.execute(
                MessageFormat.format(
                        SQL_INSERT,
                        "Product_BaggageClass_Relation",
                        queryBuilder.toString()));
    }


    /**
     * Inserts a given List of elements into the respective table in a database.
     *
     * @param <T>
     *            the generic type
     * @param someList
     *            the list to be exported
     * @param tableName
     *            the name of the table
     * @throws SQLException
     *             if the query can't be executed
     */
    public <T> void writeList(final List<T> someList, final String tableName)
            throws SQLException {
        if (someList.isEmpty()){
            throw new IllegalArgumentException("list is empty");
        }
        final StringBuilder insertQueryBuilder = new StringBuilder();
        final List<T> copiedList = new ArrayList<>(someList);
        final T lastElement = copiedList.remove(copiedList.size() - 1);
        for (final T someElement : copiedList) {
            appendToQuery(insertQueryBuilder, someElement,false);
        }
        appendToQuery(insertQueryBuilder, lastElement, true);
        statement.execute(
                MessageFormat.format(
                        SQL_INSERT,
                        tableName,
                        insertQueryBuilder.toString()));
    }

    /**
     * Write seat groups of seating models in the respective relation table.
     *
     * @param seatingModels
     *            the seating models
     * @param maxId
     *            an upper bound for the ids in this table
     * @throws SQLException
     *             if the execution of the insert query fails
     */
    public void writeSeatGroupsOfSeatingModels(
            final List<SeatingModel> seatingModels, final long maxId)
                    throws SQLException {
        long tempMaxId = maxId;
        final StringBuilder queryBuilder = new StringBuilder();
        final List<SeatingModel> copiedModels = new ArrayList<>(seatingModels);
        final SeatingModel lastModel = copiedModels.remove(
                copiedModels.size() - 1);
        for (final SeatingModel seatingModel : copiedModels) {
            tempMaxId = appendSeatGroups(
                    queryBuilder,
                    tempMaxId,
                    seatingModel,
                    false);
        }
        tempMaxId = appendSeatGroups(queryBuilder, tempMaxId, lastModel, true);
        statement.execute(
                MessageFormat.format(
                        SQL_INSERT,
                        "SeatingModel_SeatGroup_Relation",
                        queryBuilder.toString()));

    }

    /**
     * Write tariffs of flights in the respective relation table.
     *
     * @param flights
     *            the flights to be inserted
     * @param maxId
     *            an upper bound for ids in the table
     * @throws SQLException
     *             if the execution of the insert query fails
     */
    public void writeTariffsOfFlights(final List<Flight> flights,
            final long maxId)
                    throws SQLException {
        final List<Flight> copiedFlights = new ArrayList<>(flights);
        long tempMaxId = maxId;
        final StringBuilder queryBuilder = new StringBuilder();
        final Flight lastFlight = copiedFlights.remove(
                copiedFlights.size() - 1);
        for (final Flight flight : copiedFlights) {
            tempMaxId = appendTariffs(queryBuilder, tempMaxId, flight, false);
        }
        appendTariffs(queryBuilder, tempMaxId, lastFlight, true);
        if (queryBuilder.length() > 0) {
            statement.execute(
                    MessageFormat.format(
                            SQL_INSERT,
                            "Flight_Tariff_Relation",
                            queryBuilder.toString()));
        }
    }

    /**
     * Append all product-baggageClass-relations of a product to a given
     * string builder.
     *
     * @param queryBuilder
     *            the query builder
     * @param product
     *            the product
     * @param last
     *            if the value is the last one
     * @param maxId
     *            an upper bound for ids in the table
     * @return a new upper bound for ids in the table
     */
    private long appendBaggageClasses(final StringBuilder queryBuilder,
            final long maxId, final Product product, final boolean last) {
        BaggageClass lastClass = null;
        long tempMaxId = maxId;
        final List<BaggageClass> baggageClasses = new ArrayList<>(
                product.getBaggageClasses());
        if (last) {
            lastClass = baggageClasses.remove(
                    baggageClasses.size() - 1);
        }
        for (final BaggageClass baggageClass : baggageClasses) {
            appendProductBaggageClassPair(
                    queryBuilder,
                    tempMaxId,
                    product.getId(),
                    baggageClass.getId(),
                    product.getNumberOfIncludedBags().get(baggageClass),
                    false);
            tempMaxId++;
        }
        if (last) {
            appendProductBaggageClassPair(
                    queryBuilder,
                    tempMaxId,
                    product.getId(),
                    lastClass.getId(),
                    product.getNumberOfIncludedBags().get(lastClass),
                    true);
            tempMaxId++;
        }
        return tempMaxId;
    }

    /**
     * Append an value pair containing two numeric values to a given string
     * builder.
     *
     * @param queryBuilder
     *            the query builder
     * @param id
     *            one id
     * @param id2
     *            the other id
     * @param relationId
     *            unique identifier of this relation
     * @param last
     *            if the value is the last one
     */
    private void appendIdPair(final StringBuilder queryBuilder,
            final long relationId, final long id, final long id2,
            final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(relationId)));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Long.toString(id)));
        queryBuilder.append(id2);
        endAppend(queryBuilder, last);
    }

    /**
     * Append a product-baggageClass-relation as well as the corresponding
     * number of included bags to a given string builder.
     *
     * @param queryBuilder
     *            the query builder
     * @param productId
     *            id of the product
     * @param baggageClassId
     *            id of the baggage class
     * @param relationId
     *            the id of the relation
     * @param includedBags
     *            the number of included bags
     * @param last
     *            if the value is the last one
     */
    private void appendProductBaggageClassPair(
            final StringBuilder queryBuilder, final long relationId,
            final long productId,
            final long baggageClassId,
            final int includedBags, final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(relationId)));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Long.toString(productId)));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Long.toString(baggageClassId)));
        queryBuilder.append(includedBags);
        endAppend(queryBuilder, last);

    }

    /**
     * Append all seatingModel-seatGroup-relations of a seating model to a
     * respective insert query in a given string builder.
     *
     * @param queryBuilder
     *            the query builder
     * @param seatingModel
     *            the seating model
     * @param maxId
     *            an upper bound for this tables id.
     * @param last
     *            if the value is the last one
     * @return a new upper bound for this tables id
     */
    private long appendSeatGroups(final StringBuilder queryBuilder,
            final long maxId, final SeatingModel seatingModel,
            final boolean last) {
        SeatGroup lastSeatGroup = null;
        long tempMaxId = maxId;
        final List<SeatGroup> seatGroups = new ArrayList<>(
                seatingModel.getSeatGroups());
        if (last) {
            lastSeatGroup = seatGroups.remove(seatGroups.size() - 1);
        }
        for (final SeatGroup seatGroup : seatGroups) {
            appendIdPair(
                    queryBuilder,
                    tempMaxId,
                    seatingModel.getId(),
                    seatGroup.getId(),
                    false);
            tempMaxId++;
        }
        if (last) {
            appendIdPair(
                    queryBuilder,
                    tempMaxId,
                    seatingModel.getId(),
                    lastSeatGroup.getId(),
                    true);
            tempMaxId++;
        }
        return tempMaxId;
    }

    /**
     * Append all flight-tariff-relations of a flight to a given string builder.
     *
     * @param queryBuilder
     *            the query builder
     * @param flight
     *            the flight
     * @param last
     *            if the value is the last one
     * @param maxId
     *            an upper bound for ids in the table
     * @return a new upper bound for ids in the table
     */
    private long appendTariffs(final StringBuilder queryBuilder,
            final long maxId, final Flight flight, final boolean last) {
        long tempMaxId = maxId;
        if (!last) {
            for (int i = 0; i < flight.getBookableTariffs().size(); i++) {
                appendIdPair(
                        queryBuilder,
                        tempMaxId,
                        flight.getId(),
                        flight.getBookableTariffs().get(i).getId(),
                        false);
                tempMaxId++;
            }
        } else {
            final List<Tariff> bookableTariffs = new ArrayList<>(
                    flight.getBookableTariffs());
            final Tariff lastTariff = bookableTariffs.remove(
                    bookableTariffs.size() - 1);
            for (int i = 0; i < bookableTariffs.size(); i++) {
                appendIdPair(
                        queryBuilder,
                        tempMaxId,
                        flight.getId(),
                        bookableTariffs.get(i).getId(),
                        false);
                tempMaxId++;
            }
            appendIdPair(
                    queryBuilder,
                    tempMaxId,
                    flight.getId(),
                    lastTariff.getId(),
                    true);
            tempMaxId++;
        }
        return tempMaxId;
    }

    /**
     * Append a baggage class to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param baggageClass
     *            the baggage class to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final BaggageClass baggageClass, final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(baggageClass.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_STRING_INTO_SUBPATTERN,
                        baggageClass.getName()));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Long.toString(
                                baggageClass.getBaggageLimits().getId())));
        queryBuilder.append(
                Long.toString(baggageClass.getBaggagePricing().getId()));
        endAppend(queryBuilder, last);
    }


    /**
     * Append BaggageLimits object to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param element
     *            the object to be included
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final BaggageLimits element, final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(element.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(
                                element.getBaggageSize().getCircumferenceMax())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Integer.toString(element.getCountMax())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(
                                element.getBaggageSize().getHeightMax())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(
                                element.getBaggageSize().getLengthMax())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(
                                element.getBaggageSize().getWidthMax())));
        queryBuilder.append(
                Double.toString(element.getWeightMax()));
        endAppend(queryBuilder, last);
    }


    /**
     * Append a BaggagePricing object to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query.
     * @param pricing
     *            the BaggagePricing object to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder, final BaggagePricing pricing, final boolean last){
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(pricing.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(pricing.getFirstPrice())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(pricing.getSecondPrice())));
        queryBuilder.append(
                Double.toString(pricing.getAdditionalPrice()));
        endAppend(queryBuilder, last);
    }


    /**
     * Adds a SQLite Query that inserts <code>flight</code> to the database to
     * <code>queryBuilder</code>.
     *
     * @param queryBuilder
     *            builder for the partially built query that inserts flights.
     * @param flight
     *            flight to be inserted with execution of the final query
     *
     * @param lastFlight
     *            claims if the flight is the last flight in the query.
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final Flight flight,
            final boolean lastFlight) {
        final int hours = flight.getDepartureTime().getHour();
        final int minutes = flight.getDepartureTime().getMinute();
        final int day = flight.getDepartureDate().getDayOfMonth();
        final int month = flight.getDepartureDate().getMonthValue();
        final int year = flight.getDepartureDate().getYear();
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(flight.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Integer.toString(flight.getFlightNumber())));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0,number,00}:{1,number,00}\",",
                        hours,
                        minutes));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0,number,0000}-{1,number,00}-{2,number,00}\",",
                        year,
                        month,
                        day));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_STRING_INTO_SUBPATTERN,
                        flight.getRoute().getOriginAirport().getIata()));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_STRING_INTO_SUBPATTERN,
                        flight.getRoute().getDestinationAirport().getIata()));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0}\"",
                        flight.getRoute().getMarket().ordinal()));
        endAppend(queryBuilder, lastFlight);
    }


    /**
     * Append a product to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param product
     *            the product to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder, final Product product, final boolean last){
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(product.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_STRING_INTO_SUBPATTERN,
                        product.getName()));
        queryBuilder.append(
                Long.toString(product.getCompartment().getId()));
        endAppend(queryBuilder, last);
    }

    /**
     * Append a seat group to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param seatGroup
     *            the seat group to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder, final SeatGroup seatGroup, final boolean last){
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(seatGroup.getId())));
        queryBuilder.append(MessageFormat.format(INSERT_STRING_INTO_SUBPATTERN, seatGroup.getName()));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Integer.toString(seatGroup.getNumberSeats())));
        queryBuilder.append(
                Double.toString(seatGroup.getSeatPrice()));
        endAppend(queryBuilder, last);
    }


    /**
     * Append a SeatingModel object to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param seatingModel
     *            the seating model to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final SeatingModel seatingModel, final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        "({0}",
                        Long.toString(seatingModel.getId())));
        endAppend(queryBuilder, last);
    }

    /**
     * Append the value of some element to a SQlite query in a StringBuilder.
     *
     * @param <T>
     *            the generic type of the element to be added
     * @param insertQueryBuilder
     *            StringBuilder for the partially built query that inserts
     *            flights.
     * @param someElement
     *            the element to be added
     * @param last
     *            True if this is the last Element in this query
     */
    private <T> void appendToQuery(final StringBuilder insertQueryBuilder, final T someElement,
            final boolean last) {
        if (someElement instanceof Flight) {
            appendToQuery(insertQueryBuilder, (Flight) someElement, last);
        } else if (someElement instanceof BaggageClass) {
            appendToQuery(insertQueryBuilder, (BaggageClass) someElement, last);
        } else if (someElement instanceof BaggageLimits) {
            appendToQuery(
                    insertQueryBuilder,
                    (BaggageLimits) someElement,
                    last);
        } else if (someElement instanceof BaggagePricing) {
            appendToQuery(
                    insertQueryBuilder,
                    (BaggagePricing) someElement,
                    last);
        } else if (someElement instanceof Product) {
            appendToQuery(insertQueryBuilder, (Product) someElement, last);
        } else if (someElement instanceof SeatGroup) {
            appendToQuery(insertQueryBuilder, (SeatGroup) someElement, last);
        } else if (someElement instanceof SeatingModel) {
            appendToQuery(insertQueryBuilder, (SeatingModel) someElement, last);
        } else if (someElement instanceof Tariff) {
            appendToQuery(insertQueryBuilder, (Tariff) someElement, last);
        }

    }


    /**
     * Append a tariff to an insert query.
     *
     * @param queryBuilder
     *            the query builder containing the partial query
     * @param tariff
     *            the tariff to be inserted
     * @param last
     *            if the value is the last one
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final Tariff tariff, final boolean last) {
        queryBuilder.append(
                MessageFormat.format(
                        START_QUERY_WITH_NONSTRING_SUBPATTERN,
                        Long.toString(tariff.getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Double.toString(tariff.getPrice())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        Long.toString(tariff.getProduct().getId())));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_NONSTRING_INTO_SUBPATTERN,
                        tariff.getMarket().ordinal()));
        queryBuilder.append(Long.toString(tariff.getSeating().getId()));
        endAppend(queryBuilder, last);
    }


    /**
     * Appends the end of a value to an insert query in a StringBuilder.
     *
     * @param queryBuilder
     *            the query builder
     * @param last
     *            if the value is the last one
     */
    private void endAppend(final StringBuilder queryBuilder,
            final boolean last) {
        if (last) {
            queryBuilder.append(END_OF_LAST_VALUE_SUBPATTERN);
        } else {
            queryBuilder.append(END_OF_VALUE_SUBPATTERN);
        }
    }

}
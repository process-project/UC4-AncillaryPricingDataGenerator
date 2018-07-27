package com.lhsystems.module.datageneratorancillary.service.write.sqlite;

import com.lhsystems.module.datageneratorancillary.service.Flight;
import com.lhsystems.module.datageneratorancillary.service.Market;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Offers connection to sqlite database. Allows writing flight to said database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class SqliteWriter {

    /**
     * Pattern for adding unformatted flight attributes to a insert query.
     */
    private static final String INSERT_INTO_SUBPATTERN = "\"{0}\",";

    /**
     * Pattern for a SQLite query that inserts Flights into the database.
     */
    private static final String SQL_INSERT_FLIGHTS = "INSERT INTO Flight values {0};";

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
     * Inserts a given List of Flights into the SQLite Database given by
     * <code>this.connection</code>. The query is built and executed after such
     * that only on execution is necessary
     *
     * @param flights
     *            list of Flights to be inserted into the SQLite database
     * @param mapMarketToMarketId
     *            HashMap to assign to each market it's respective Id
     * @throws SQLException
     *             if <code>SQL_INSERT_FLIGHTS</code> can't be executed
     */
    public void write(final ArrayList<Flight> flights,
            final HashMap<Market, Integer> mapMarketToMarketId)
                    throws SQLException {
        final StringBuilder queryBuilder = new StringBuilder();
        final Flight lastFlight = flights.remove(flights.size());
        for (final Flight flight : flights) {
            appendToQuery(queryBuilder, flight, mapMarketToMarketId, false);
        }
        appendToQuery(queryBuilder, lastFlight, mapMarketToMarketId, true);
        statement.execute(
                MessageFormat.format(
                        SQL_INSERT_FLIGHTS,
                        queryBuilder.toString()));
    }

    /**
     * Adds a SQLite Query that inserts <code>flight</code> to the database to
     * <code>queryBuilder</code>.
     *
     * @param queryBuilder
     *            builder for the partially built query that inserts flights.
     * @param flight
     *            flight to be inserted with execution of the final query
     * @param idOfMarket
     *            maps a market to the corresponding Id in the table Market.
     * @param lastFlight
     *            claims if the flight is the last flight in the query.
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final Flight flight, final HashMap<Market, Integer> idOfMarket,
            final boolean lastFlight) {
        final int hours = flight.getDepartureTime().getHour();
        final int minutes = flight.getDepartureTime().getMinute();
        final int day = flight.getDepartureDate().getDayOfMonth();
        final int month = flight.getDepartureDate().getMonthValue();
        final int year = flight.getDepartureDate().getYear();
        queryBuilder.append(MessageFormat.format("(\"{0}\",", flight.getId()));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_INTO_SUBPATTERN,
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
                        INSERT_INTO_SUBPATTERN,
                        flight.getOriginAirport()));
        queryBuilder.append(
                MessageFormat.format(
                        INSERT_INTO_SUBPATTERN,
                        flight.getDestinationAirport()));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0}\")",
                        idOfMarket.get(flight.getMarket())));
        if (!lastFlight) {
            queryBuilder.append(",\n");
        }

    }

}
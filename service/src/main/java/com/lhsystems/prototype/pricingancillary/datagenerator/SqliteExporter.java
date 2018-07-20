package com.lhsystems.prototype.pricingancillary.datagenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * offers connection to sqlite database. Allows reading and writing in said
 * database.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */

final public class SqliteExporter {

    /**
     * Query to get the maximum used id currently in the database
     */
    private final static String SQL_SELECT_MAX_ID = "SELECT max(id) FROM Flight";

    private final static String SQL_SELECT_MARKETS = "SELECT * FROM Market";

    /**
     * A connection with a specific database. SQL statements are executed and
     * results are returned within the context of this connection.
     */
    private final Connection connection;

    /**
     * used for executing a SQL statement and returning the results.
     */
    private final Statement statement;

    /**
     * table of data resulting from executing <code>statement</code>
     */
    private ResultSet resultSet;

    /**
     * Inserts a given List of Flights into the SqlDatabase given by
     * <code>this.connection</code>. The flights are added one by one.
     *
     * @param flights
     * @throws SQLException
     */
    public void exportToSqlite(final ArrayList<Flight> flights)
            throws SQLException {
        final HashMap<Market, Integer> mapMarketIdToMarket = getMarkets();
        final StringBuilder queryBuilder = new StringBuilder();
        for (final Flight flight: flights){
            appendToQuery(queryBuilder, flight, mapMarketIdToMarket);
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        statement.execute(
                "INSERT INTO Flight values "
                        + queryBuilder.toString()
                        + ";");
    }

    /**
     * Gets the lexicographic maximal Id appearing in the database and decodes
     * it from a hexadecimal string to a Long. Using this id during
     * initialization of a FlightGenerator guarantees that new generated flights
     * have unique Ids.
     *
     * @return
     * @throws SQLException
     */
    public Long readMaxId() throws SQLException {
        resultSet = statement.executeQuery(SQL_SELECT_MAX_ID);
        return (long) resultSet.getInt("max(id)");
    }

    /**
     * Constructor. connects to sqlite dataBase specified with
     * <code>dbPath</code>
     *
     * @param dbPath
     *            path of the sqlite dataBase to connect to.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SqliteExporter(final String dbPath)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(
                "jdbc:sqlite:" + dbPath);
        statement = connection.createStatement();
        resultSet = null;
    }

    /**
     * Adds a SQLite Query that inserts <code>flight</code> to the database to
     * <code>queryBuilder</code>.
     *
     * @param queryBuilder
     * @param flight
     *            flight to be inserted with execution of the final query
     * @param idOfMarket
     *            maps a market to the corresponding Id in the table Market
     */
    private void appendToQuery(final StringBuilder queryBuilder,
            final Flight flight, final HashMap<Market, Integer> idOfMarket) {
        final int hours = flight.getDepartureTime().getHour();
        final int minutes = flight.getDepartureTime().getMinute();
        final int day = flight.getDepartureDate().getDayOfMonth();
        final int month = flight.getDepartureDate().getMonthValue();
        final int year = flight.getDepartureDate().getYear();
        queryBuilder.append(MessageFormat.format("(\"{0}\",", flight.getId()));
        queryBuilder.append(MessageFormat.format("\"{0}\",",Integer.toString(flight.getFlightNumber())));
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
                MessageFormat.format("\"{0}\",", flight.getOriginAirport()));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0}\",",
                        flight.getDestinationAirport()));
        queryBuilder.append(
                MessageFormat.format(
                        "\"{0}\"),\n",
                        idOfMarket.get(flight.getMarket())));



    }

    /**
     * Returns a HashMap that maps each market to its respective id in the
     * database.
     *
     * @return
     * @throws SQLException
     */
    private HashMap<Market, Integer> getMarkets() throws SQLException {
        final HashMap<Market, Integer> mapMarketIdToMarket = new HashMap<>();
        resultSet = statement.executeQuery(SQL_SELECT_MARKETS);
        while (resultSet.next()){
            mapMarketIdToMarket.put(
                    Market.valueOf(resultSet.getString(2)),
                    resultSet.getInt(1));
        }
        return mapMarketIdToMarket;
    }

}
package com.lhsystems.module.datageneratorancillary.service.read.sqlite;

import com.lhsystems.module.datageneratorancillary.service.Airport;
import com.lhsystems.module.datageneratorancillary.service.Market;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Reads data from a SQLite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class SqliteReader {

    /**
     * Name of the column that contains the market Id associated with the
     * airport in the resultset returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    private static final String COLUMN_AIRPORTS_MARKET_ID = "MARKET";

    /**
     * Name of the column that contains the airports name in the resultset
     * returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    private static final String COLUMN_AIRPORTS_NAME = "NAME";

    /**
     * Name of the column that contains the airports IATA code in the resultset
     * returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    private static final String COLUMN_AIRPORTS_IATA = "IATA";

    /**
     * Name of the column that contains the Market Name in the resultset
     * returned by <code>SQL_SELECT_MARKETS</code>.
     */
    private static final String COLUMN_MARKET_NAME = "MARKET";

    /**
     * Name of the column that contains the Market Id in the resultset returned
     * by <code>SQL_SELECT_MARKETS</code>.
     */
    private static final String COLUMN_MARKET_ID = "ID";

    /**
     * Column that contains the maximum id in the resultset returned by
     * <code>SQL_SELECT_MAX_ID</code>.
     */
    private static final String COLUMN_MAX_ID = "max(id)";

    /**
     * Query to select all Airports currently in the database.
     */
    private static final String SQL_SELECT_AIRPORTS = "SELECT * FROM Airport";

    /**
     * Query to get all markets currently in the database.
     */
    private static final String SQL_SELECT_MARKETS = "SELECT * FROM Market";

    /**
     * Query to get the maximum used id currently in the database.
     */
    private static final String SQL_SELECT_MAX_ID = "SELECT max(id) FROM Flight";

    /**
     * A connection with a specific database. SQL statements are executed and
     * results are returned within the context of this connection.
     */
    private final Connection connection;

    /**
     * Table of data resulting from executing <code>statement</code>.
     */
    private ResultSet resultSet;

    /**
     * Used for executing a SQL statement and returning the results.
     */
    private final Statement statement;

    /**
     * Constructor.
     *
     * @param paramConnection
     *            Connection to an SQLite Database.
     * @throws SQLException
     *             if the connection can't create a statement.
     */
    public SqliteReader(final Connection paramConnection) throws SQLException {
        connection = paramConnection;
        statement = connection.createStatement();
        resultSet = null;
    }

    /**
     * Reads all Airports currently in the database and returns them as an
     * ArrayList.
     *
     * @return ArrayList containing all Airports currently in the database
     * @throws SQLException
     *             if the query <code>"SQL_SELECT_AIRPORTS"</code> can't be
     *             executed
     */
    public final List<Airport> getAirports() throws SQLException {
        final ArrayList<Airport> airports = new ArrayList<>();
        resultSet = statement.executeQuery(SQL_SELECT_AIRPORTS);
        final Market[] markets = Market.values().clone();
        while (resultSet.next()) {
            airports.add(
                    new Airport(
                            resultSet.getString(COLUMN_AIRPORTS_IATA),
                            resultSet.getString(COLUMN_AIRPORTS_NAME),
                            markets[resultSet.getInt(
                                    COLUMN_AIRPORTS_MARKET_ID)]));
        }
        return airports;
    }

    /**
     * Returns a HashMap that maps each market to its respective id in the
     * database.
     *
     * @return HashMap that maps each market to its respective id in the
     *         database.
     * @throws SQLException
     *             if <code>SQL_SELECT_MARKETS</code> can't be executed.
     */
    public final HashMap<Market, Integer> getMarkets() throws SQLException {
        final HashMap<Market, Integer> mapMarketIdToMarket = new HashMap<>();
        resultSet = statement.executeQuery(SQL_SELECT_MARKETS);
        while (resultSet.next()) {
            mapMarketIdToMarket.put(
                    Market.valueOf(resultSet.getString(COLUMN_MARKET_NAME)),
                    resultSet.getInt(COLUMN_MARKET_ID));
        }
        return mapMarketIdToMarket;
    }

    /**
     * Gets the maximal Id appearing in the database. Using this id during
     * initialization of a FlightGenerator guarantees that new generated flights
     * have unique Ids.
     *
     * @return the maximal Id appearing in the database
     * @throws SQLException
     *             if <code>SQL_SELECT_MAX_ID</code> can't be executed.
     */
    public final long getMaxId() throws SQLException {
        resultSet = statement.executeQuery(SQL_SELECT_MAX_ID);
        return resultSet.getInt(COLUMN_MAX_ID);
    }
}

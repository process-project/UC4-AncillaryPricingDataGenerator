package com.lhsystems.module.data.generator.ancillary.sqlite.read;

import com.lhsystems.module.data.generator.ancillary.data.Market;
import com.lhsystems.module.data.generator.ancillary.data.Airport;
import com.lhsystems.module.data.generator.ancillary.data.Compartment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads data from a SQLite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SqliteReader {

    /**
     * Name of the column that contains the market Id associated with the
     * airport in the resultset returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    public static final String COLUMN_AIRPORTS_MARKET_ID = "MARKET";

    /**
     * Name of the column that contains the airports name in the resultset
     * returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    public static final String COLUMN_AIRPORTS_NAME = "NAME";

    /**
     * Name of the column that contains the airports IATA code in the resultset
     * returned by <code>SQL_SELECT_AIRPORTS</code>.
     */
    public static final String COLUMN_AIRPORTS_IATA = "IATA";

    /**
     * Name of the column that contains the market mame in the resultset
     * returned by <code>SQL_SELECT_MARKETS</code>.
     */
    public static final String COLUMN_MARKET_NAME = "MARKET";

    /**
     * Name of the column that contains the market id in the resultset returned
     * by <code>SQL_SELECT_MARKETS</code>.
     */
    public static final String COLUMN_MARKET_ID = "ID";

    /**
     * Column that contains the maximum id in the resultset returned by querys
     * in the pattern <code>SQL_SELECT_MAX_ID_PATTERN</code>.
     */
    public static final String COLUMN_MAX_ID = "max(ID)";

    /**
     * The column that contains the table names in the result returned by
     * <code>SQL_SELECT_TABLES</code>.
     */
    public static final String COLUMN_TABLE_INDEX = "name";

    /**
     * Query to select all airports currently in the database.
     */
    public static final String SQL_SELECT_AIRPORTS = "SELECT * FROM Airport";

    /** Query to select all compartments currently in the database. */
    public static final String SQL_SELECT_COMPARTMENTS = "SELECT * FROM Compartment";

    /**
     * Query to get all markets currently in the database.
     */
    public static final String SQL_SELECT_MARKETS = "SELECT * FROM Market";

    /** Query to get the names of all tables in the database. */
    public static final String SQL_SELECT_TABLES = "SELECT name FROM sqlite_master WHERE type='table';";

    /**
     * Column that contains the compartment id in the resultset returned by
     * <code>SQL_SELECT_COMPARTMENTS</code>.
     */
    private static final String COLUMN_COMPARTMENTS_ID = "ID";

    /**
     * Column that contains the compartment name in the resultset returned by
     * <code>SQL_SELECT_COMPARTMENTS</code>.
     */
    private static final String COLUMN_COMPARTMENTS_NAME = "NAME";

    /**
     * Query to get the maximum used id in a given table.
     */
    private static final String SQL_SELECT_MAX_ID_PATTERN = "SELECT max(ID) FROM {0};";

    /** The column that contains the identifying char of the compartments. */
    private static final String COLUMN_COMPARTMENTS_ID_CHAR = "CHARID";

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
     * Reads all airports currently in the database and returns them as an list.
     *
     * @return list containing all airports currently in the database
     * @throws SQLException
     *             if the query <code>SQL_SELECT_AIRPORTS</code> can't be
     *             executed
     */
    public List<Airport> getAirports() throws SQLException {
        final List<Airport> airports = new ArrayList<>();
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
     * Reads all compartments from the respective data table and returns them as
     * a list.
     *
     * @return a list of compartments
     * @throws SQLException
     *             if the query <code>SQL_SELECT_COMPARTMENTS</code> can't be
     *             executed
     */
    public List<Compartment> getCompartments() throws SQLException {
        final List<Compartment> compartments = new ArrayList<>();
        resultSet = statement.executeQuery(SQL_SELECT_COMPARTMENTS);
        while (resultSet.next()) {
            compartments.add(
                    new Compartment(
                            resultSet.getInt(COLUMN_COMPARTMENTS_ID),
                            resultSet.getString(
                                    COLUMN_COMPARTMENTS_ID_CHAR).charAt(0),
                            resultSet.getString(COLUMN_COMPARTMENTS_NAME)
                            ));
        }
        return compartments;
    }

    /**
     * Gets the maximal Id appearing in the database. Using this id during
     * initialization of a DataGenerator guarantees that new generated objects
     * have unique Ids.
     *
     * @return the maximal Id appearing in the database
     * @throws SQLException
     *             if <code>SQL_SELECT_MAX_ID</code> can't be executed.
     */
    public long getMaxId() throws SQLException {
        final ResultSet tableSet;
        tableSet = statement.executeQuery(SQL_SELECT_TABLES);
        final List<String> tables = new ArrayList<>();
        while (tableSet.next()){
            tables.add(tableSet.getString(COLUMN_TABLE_INDEX));
        }
        int maxId = 0;
        for (final String table : tables) {
            resultSet = statement.executeQuery(
                    MessageFormat.format(
                            SQL_SELECT_MAX_ID_PATTERN,
                            table));
            if (resultSet.getInt(COLUMN_MAX_ID) > maxId) {
                maxId = resultSet.getInt(COLUMN_MAX_ID);
            }
        }
        return maxId;
    }
}

package com.lhsystems.module.data.generator.ancillary.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens connection to a SQLite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SqliteConnection {

    /** The connection. */
    private final Connection connection;

    /**
     *
     * Instantiates a new SQLite connection to the database given by
     * <code>dataBasePath</code>.
     *
     * @param dataBasePath
     *            the path of the database.
     * @throws SQLException
     *             if the SQLite connection can't be opened.
     * @throws ClassNotFoundException
     *             exception for when "org.sqlite.JDBC" isn't found.
     */
    public SqliteConnection(final String dataBasePath)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(
                "jdbc:sqlite:" + dataBasePath);
    }

    /**
     * Returns the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}

package com.lhsystems.module.datageneratorancillary.service.read.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens connection to a SQLite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class SqliteConnection {

    /** The connection. */
    private final Connection connection;

    /**
     *
     * Instantiates a new SQLite connection.
     *
     * @param dbPath
     *            the path of the database.
     * @throws SQLException
     *             if the SQLite connection can't be opened.
     * @throws ClassNotFoundException
     *             exception for when "org.sqlite.JDBC" isn't found.
     */
    public SqliteConnection(final String dbPath)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(
                "jdbc:sqlite:" + dbPath);
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public final Connection getConnection() {
        return connection;
    }
}

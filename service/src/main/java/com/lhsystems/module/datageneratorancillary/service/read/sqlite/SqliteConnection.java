package com.lhsystems.module.datageneratorancillary.service.read.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Opens connection to a SQLite database.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */
public class SqliteConnection {

    private Connection connection;
    
    /**
     *
     * Instantiates a new SQLite connection.
     *
     * @param dbPath
     *            the path of the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public SqliteConnection(final String dbPath)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        final Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:" + dbPath);
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}

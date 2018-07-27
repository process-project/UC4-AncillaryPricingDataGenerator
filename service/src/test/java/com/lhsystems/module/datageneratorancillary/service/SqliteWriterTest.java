package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.write.sqlite.SqliteWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests SQLWriter.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(MockitoJUnitRunner.class)
public class SqliteWriterTest {

    /** Query to insert flights into a table. */
    private static final String INSERT_INTO_QUERY = "INSERT INTO Flight values (\"1\",\"1\",\"12:00\",\"2018-05-05\",\"FRA\",\"TXL\",\"0\");";

    /** Mocks a SQLite connection. */
    @Mock
    private Connection connection;

    /** Mocks a SQL statement. */
    @Mock
    private Statement statement;


    /** Mocks a SQL reultSet. */
    @Mock
    private ResultSet resultSet;

    /**
     * Instantiates a new sqlite writer test.
     */
    public SqliteWriterTest() {
    }

    /**
     * sets up the behavior of mocked objects.
     *
     * @throws SQLException
     *             From SQL related methods
     */
    @Before
    public final void setUp() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
    }

    /**
     * Tests <code>write</code>.
     *
     * @throws SQLException
     *             the SQL exception from <code>write</code>
     */
    @Test
    public final void testWrite() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Flight> flights = new ArrayList<>();
        flights.add(
                new Flight(
                        1,
                        1,
                        LocalDateTime.of(2018, 5, 5, 12, 0),
                        "FRA",
                        "TXL",
                        Market.DOMESTIC));
        final HashMap<Market,Integer> mapToId = new HashMap<>();
        mapToId.put(Market.DOMESTIC, 0);
        sqliteWriter.write(flights, mapToId);
        verify(statement).execute(INSERT_INTO_QUERY);
    }
}

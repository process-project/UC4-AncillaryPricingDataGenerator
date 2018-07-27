package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.read.sqlite.SqliteReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests SqliteReader.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class SqliteReaderTest {

    /** Mocks a SQLite connection. */
    @Mock
    private Connection connection;

    /** Mocks a SQL statement. */
    @Mock
    private Statement statement;

    /** Mocks a SQL reultSet. */
    @Mock
    private ResultSet resultSet;

    /** List containing airports. */
    private final ArrayList<Airport> airportList = new ArrayList<>();

    /**
     * Instantiates a new sqlite reader test.
     */
    public SqliteReaderTest() {
    }

    /**
     * sets up the behavior of mocked objects.
     *
     * @throws SQLException
     *             From various SQL related methods
     */
    @Before
    public final void setUp() throws SQLException {
        airportList.add(
                new Airport("FRA", "Frankfurt Airport", Market.DOMESTIC));
        MockitoAnnotations.initMocks(this);
        when(connection.createStatement()).thenReturn(statement);

        when(
                statement.executeQuery(
                        SqliteReader.SQL_SELECT_AIRPORTS)).thenReturn(
                                resultSet);
        when(resultSet.getString(SqliteReader.COLUMN_AIRPORTS_IATA)).thenReturn(
                airportList.get(0).getIata());
        when(resultSet.getString(SqliteReader.COLUMN_AIRPORTS_NAME)).thenReturn(
                airportList.get(0).getName());
        when(resultSet.next()).thenReturn(true);
        when(
                resultSet.getInt(
                        SqliteReader.COLUMN_AIRPORTS_MARKET_ID)).thenAnswer(
                                new Answer<Integer>() {
                                    @Override
                                    public Integer answer(final InvocationOnMock invocation)
                                            throws SQLException {
                                        when(resultSet.next()).thenReturn(false);
                                        return 0;
                                    }
                                });
    }


    /**
     * Tests getAirports().
     *
     * @throws SQLException
     *             if something went wrong during communication with the SQLite
     *             database
     */
    @Test
    public final void testGetAirports() throws SQLException {
        final SqliteReader sqliteReader = new SqliteReader(connection);
        for (final Airport airport : sqliteReader.getAirports()) {
            assertEquals(airportList.get(0), airport);
        }
    }


}

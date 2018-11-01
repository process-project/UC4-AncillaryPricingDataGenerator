package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.sqlite.read.SqliteReader;

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

    /** List containing airports. */
    private final ArrayList<Airport> airportList = new ArrayList<>();

    /** Mocks a SQL resultSet containing maximal id values. */
    @Mock
    private ResultSet maxIdSet;

    /** Mocks a SQL resultSet. */
    @Mock
    private ResultSet resultSet;

    /** Mocks a SQL statement. */
    @Mock
    private Statement statement;

    /** Mocks a SQL resultSet containing table names. */
    @Mock
    private ResultSet tableSet;

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
        when(statement.executeQuery(SqliteReader.SQL_SELECT_TABLES)).thenReturn(
                tableSet);
        when(tableSet.next()).thenReturn(true);
        when(tableSet.getString(SqliteReader.COLUMN_TABLE_INDEX)).thenAnswer(
                new Answer<String>() {
                    @Override
                    public String answer(final InvocationOnMock invocation)
                            throws SQLException {
                        when(tableSet.next()).thenReturn(false);
                        return "tableName";
                    }
                });
        when(
                statement.executeQuery(
                        "SELECT max(ID) FROM tableName;")).thenReturn(maxIdSet);
        when(maxIdSet.getInt(SqliteReader.COLUMN_MAX_ID)).thenReturn(13);
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

    /**
     * Test getMaxId().
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testGetMaxId() throws SQLException {
        final SqliteReader sqliteReader = new SqliteReader(connection);
        assertEquals(sqliteReader.getMaxId(), 13);
    }

}

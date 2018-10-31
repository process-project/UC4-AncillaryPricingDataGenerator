package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.sqlite.write.SqliteWriter;
import com.lhsystems.module.datageneratorancillary.service.utils.OptionFileKeys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /** Query to insert a baggage class into a table. */
    private static final String INSERT_BAGGAGE_CLASS_QUERY = "INSERT INTO BaggageClass values (1,\"baggageClass\",1,1);";

    /** Query to insert flights into a table. */
    private static final String INSERT_FLIGHTS_QUERY = "INSERT INTO Flight values (1,1,\"12:00\",\"2018-05-05\",\"TAC\",\"TAD\",\"1\");";

    /** Query to insert tariffs into a table. */
    private static final String INSERT_TARIFFS_QUERY = "INSERT INTO Tariff values (1,3.0,1,1,1);";

    /** Query to insert a product into a table. */
    private static final String INSERT_PRODUCTS_QUERY = "INSERT INTO Product values (1,\"product\",1);";

    /** Query to insert a seat group into a table. */
    private static final String INSERT_SEAT_GROUP_QUERY = "INSERT INTO SeatGroup values (1,\"seatGroup\",1,1.0);";

    /** Query to insert a seating model into a table. */
    private static final String INSERT_SEATING_MODEL_QUERY = "INSERT INTO SeatingModel values (1);";

    /**
     * Query to insert a Product-BaggageClass Relation into the respective
     * table.
     */
    private static final String INSERT_BAGGAGE_CLASSES_OF_PRODUCTS_QUERY = "INSERT INTO Product_BaggageClass_Relation values (1,1,1,1);";

    /** Query to insert a Flight-Tariff Relation into the respective table. */
    private static final String INSERT_TARIFFS_OF_FLIGHTS = "INSERT INTO Flight_Tariff_Relation values (1,1,1),\n(2,1,3);";

    /**
     * Query to insert a SeatingModel-SeatGroup Relation into the respective
     * table.
     */
    private static final String INSERT_SEAT_GROUPS_OF_SEATING_MODELS = "INSERT INTO SeatingModel_SeatGroup_Relation values (1,1,1),\n(2,2,1),\n(3,2,2);";

    /** One airport for testing. */
    private Airport airport1;

    /** Second airport for testing. */
    private Airport airport2;

    /** Mocks a SQLite connection. */
    @Mock
    private Connection connection;

    /** Mocks a SQL statement. */
    @Mock
    private Statement statement;

    /** Mocks a SQL resultSet. */
    @Mock
    private ResultSet resultSet;

    /** The baggage class for testing. */
    private BaggageClass baggageClass;

    /** The baggage limits for testing. */
    private BaggageLimits baggageLimits;

    /** The baggage pricing for testing. */
    private BaggagePricing baggagePricing;

    /** The baggage size for testing. */
    private BaggageSize baggageSize;

    /** The compartment for testing. */
    private Compartment compartment;

    /** The product for testing. */
    private Product product;

    /** The seat group for testing. */
    private SeatGroup seatGroup;

    /** The seating model for testing. */
    private SeatingModel seatingModel;

    /** tariff for testing. */
    private Tariff tariff;

    /**
     * Instantiates a new sqlite writer test.
     */
    public SqliteWriterTest() {
    }


    /**
     * sets up the behavior of mocked objects and initializes used object.
     *
     * @throws SQLException
     *             From SQL related methods
     */
    @Before
    public final void setUp() throws SQLException {
        airport1 = new Airport(
                "TAC",
                "Test Airport Continental",
                Market.CONTINENTAL);
        airport2 = new Airport("TAD", "Test Airport Domestic", Market.DOMESTIC);
        baggageSize = new BaggageSize(3, 3, 3, 3);
        baggageLimits = new BaggageLimits(baggageSize, 3, 3);
        baggagePricing = new BaggagePricing(3, 3, 3);
        baggageClass = new BaggageClass(
                "baggageClass",
                baggageLimits,
                baggagePricing);
        compartment = new Compartment('N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        product = new Product(
                "product",
                compartment,
                baggageClasses,
                includedBags);
        seatGroup = new SeatGroup("seatGroup", 1, 1);
        final ArrayList<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
        seatingModel = new SeatingModel(seatGroups);
        tariff = new Tariff(3, seatingModel, product, Market.CONTINENTAL);
        when(connection.createStatement()).thenReturn(statement);
    }

    /**
     * Test writing of BaggageClass-Product-Relation. In particular we test for
     * a list of products if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteBaggageClassesOfProducts()
            throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sqliteWriter.writeBaggageClassesOfProducts(products, 1);
        verify(statement).execute(
                INSERT_BAGGAGE_CLASSES_OF_PRODUCTS_QUERY);
    }

    /**
     * Test writing of baggageClass list. In particular we test for a list of
     * baggage classes if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteBaggageClassList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        sqliteWriter.writeList(
                baggageClasses,
                OptionFileKeys.BAGGAGE_CLASS_NAME);
        verify(statement).execute(INSERT_BAGGAGE_CLASS_QUERY);
    }

    /**
     * Test writing of product list. In particular we test for a list of
     * products if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteProductList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        sqliteWriter.writeList(
                products,
                OptionFileKeys.PRODUCT_NAME);
        verify(statement).execute(INSERT_PRODUCTS_QUERY);
    }

    /**
     * Test writing of seatGroup list. In particular we test for a list of seat
     * groups, if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteSeatGroupList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
        sqliteWriter.writeList(
                seatGroups,
                OptionFileKeys.SEAT_GROUP_NAME);
        verify(statement).execute(INSERT_SEAT_GROUP_QUERY);
    }

    /**
     * Test writing of seatingModel list. In particular we test for a list of
     * seating models, if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteSeatingModelList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<SeatingModel> seatingModels = new ArrayList<>();
        seatingModels.add(seatingModel);
        sqliteWriter.writeList(
                seatingModels,
                OptionFileKeys.SEATING_MODEL_NAME);
        verify(statement).execute(INSERT_SEATING_MODEL_QUERY);
    }

    /**
     * Test writing of tariff list. In particular we test for a list of tariffs,
     * if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteTariffList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        sqliteWriter.writeList(tariffs, OptionFileKeys.TARIFF_NAME);
        verify(statement).execute(INSERT_TARIFFS_QUERY);
    }

    /**
     * Tests writing of flight lists. In particular we test for a list of
     * flights, if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception from <code>write</code>
     */
    @Test
    public final void testWriteFlightList() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Flight> flights = new ArrayList<>();
        final ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        flights.add(
                new Flight(
                        1,
                        LocalDateTime.of(2018, 5, 5, 12, 0),
                        new Route(airport1, airport2),
                        tariffs));
        sqliteWriter.writeList(flights, OptionFileKeys.FLIGHT_NAME);
        verify(statement).execute(INSERT_FLIGHTS_QUERY);
    }

    /**
     * Tests writing of Flight-Tariff Relation. In particular we test for a list
     * of flights, if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteTariffsOfFlights() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<Flight> flights = new ArrayList<>();
        final ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        tariffs.add(
                new Tariff(3, seatingModel, product, Market.CONTINENTAL));
        flights.add(
                new Flight(
                        1,
                        LocalDateTime.of(2018, 5, 5, 12, 0),
                        new Route(airport1, airport2),
                        tariffs));
        sqliteWriter.writeTariffsOfFlights(flights, 1);
        verify(statement).execute(INSERT_TARIFFS_OF_FLIGHTS);
    }

    /**
     * Tests writing SeatingModel-SeatGroup Relation. In particular we test for
     * a list of seating models, if the correct query is built
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Test
    public final void testWriteSeatGroupsOfSeatingModels() throws SQLException {
        final SqliteWriter sqliteWriter = new SqliteWriter(connection);
        final ArrayList<SeatingModel> seatingModels = new ArrayList<>();
        final ArrayList<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
        seatGroups.add(new SeatGroup("someName", 6, 6));
        seatingModels.add(seatingModel);
        seatingModels.add(new SeatingModel(seatGroups));
        sqliteWriter.writeSeatGroupsOfSeatingModels(seatingModels, 1);
        verify(statement).execute(INSERT_SEAT_GROUPS_OF_SEATING_MODELS);
    }
}
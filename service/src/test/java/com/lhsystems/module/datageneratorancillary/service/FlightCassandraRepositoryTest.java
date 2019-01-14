package com.lhsystems.module.datageneratorancillary.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.lhsystems.module.datageneratorancillary.service.cassandra.data.FlightCassandra;
import com.lhsystems.module.datageneratorancillary.service.cassandra.repository.FlightCassandraRepository;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CassandraConfig.class})
public class FlightCassandraRepositoryTest {

    private static final String DATA_TABLE_NAME = "flight";


    @Autowired
    private FlightCassandraRepository flightCassandraRepository;


    public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS testKeySpace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '3' };";

    public static final String KEYSPACE_ACTIVATE_QUERY = "USE testKeySpace;";


    @Autowired
    private CassandraAdminOperations adminTemplate;

    @BeforeClass
    public static void startCassandraEmbedded() throws InterruptedException, TTransportException, ConfigurationException, IOException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
        final Session session = cluster.connect();
        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        Thread.sleep(5000);
    }

    @Before
    public void createTable() throws InterruptedException, TTransportException, ConfigurationException, IOException {
        adminTemplate.createTable(true, CqlIdentifier.cqlId(DATA_TABLE_NAME), Flight.class, new HashMap<String, Object>());
    }

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @After
    public void dropTable() {
        adminTemplate.dropTable(CqlIdentifier.cqlId(DATA_TABLE_NAME));
    }

    @AfterClass
    public static void stopCassandraEmbedded() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

    @Test
    public void testSavingFlightEntity(){
        LocalDateTime date = LocalDateTime.of(2018, 7, 9, 1, 12, 0);
        UUID uuid = UUIDs.timeBased();
        FlightCassandra flightCassandra = new FlightCassandra(date, 1, uuid, "KRK", "FRA", "DOMESTIC");
        FlightCassandra saved = flightCassandraRepository.save(flightCassandra);

        Assert.assertEquals(saved, flightCassandra);
    }




}
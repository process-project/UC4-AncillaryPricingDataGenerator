package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.FlightCassandra;
import com.lhsystems.module.datageneratorancillary.service.cassandra.FlightCassandraRepository;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.FlightConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.FlightGenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Starts generating flight entities and save them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class FlightGeneratorStarter {

    /** The repository used for saving flights. */
    //private final FlightRepository flightRepository;
    private final FlightCassandraRepository flightCassandraRepository;

    /**
     * Instantiates a new flight generator starer with injected flight repository.
     *
     * @param flightRepositoryParam
     *              repository responsible for crud operations on flight entities
     * @param flightCassandraRepositoryParam
     *          repository responsible for crud operations on flight entities
     */
    @Autowired
    public FlightGeneratorStarter(final FlightCassandraRepository flightCassandraRepositoryParam) {
       // flightRepository = flightRepositoryParam;
        flightCassandraRepository = flightCassandraRepositoryParam;
    }

    /**
     * Generate flight entities in flight generator and save them.
     *
     * @param flightConfiguration
     *        the options used for flight generator
     * @param tariffs
     *        the tariffs to be used for flight generation
     * @param routes
     *        the routes to be used for flight generation
     * @return
     *        the list of generated flights
     */
    public List<Flight> generateFlightsEntities(
            final FlightConfiguration flightConfiguration,
            final List<Tariff> tariffs, final List<Route> routes) {
        final FlightGenerator flightGenerator = new FlightGenerator(
                routes,
                tariffs,
                flightConfiguration);
        final List<Flight> flights = flightGenerator.generateList(
                flightConfiguration.getNumberFlight());
        flights.stream()
                .map(FlightCassandra::new)
                .forEach(flightCassandraRepository::save);
        return flights;
        /*return flights.stream()
                .map(flightRepository::save)
                .collect(Collectors.toList())*/
    }
}

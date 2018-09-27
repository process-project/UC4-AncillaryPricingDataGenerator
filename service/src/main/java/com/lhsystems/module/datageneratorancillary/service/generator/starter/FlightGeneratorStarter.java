package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.FlightGeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.FlightGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Starts generating flight entities and save them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
class FlightGeneratorStarter {

    /** The repository used for saving flights. */
    private final FlightRepository flightRepository;

    /**
     * Instantiates a new flight generator starer with injected flight repository.
     *
     * @param flightRepositoryParam
     *              repository responsible for crud operations on flight entities
     */
    @Autowired
    public FlightGeneratorStarter(final FlightRepository flightRepositoryParam) {
        this.flightRepository = flightRepositoryParam;
    }

    /**
     * Generate flight entities in flight generator and save them.
     *
     * @param startId
     *        the smallest id used for data Generation
     * @param flightGeneratorConfiguration
     *        the options used for flight generator
     * @param tariffs
     *        the tariffs to be used for flight generation
     * @param routes
     *        the routes to be used for flight generation
     * @return
     *        the list of generated flights
     */
    List<Flight> generateFlightsEntities(final long startId, final FlightGeneratorConfiguration flightGeneratorConfiguration,
                                         final List<Tariff> tariffs, final List<Route> routes) {
        final FlightGenerator flightGenerator = new FlightGenerator(startId, routes, tariffs,
                flightGeneratorConfiguration.getMinFlightDateAsLocalDate(),
                flightGeneratorConfiguration.getMaxFlightDateAsLocalDate());
        final List<Flight> flights = flightGenerator.generateList(flightGeneratorConfiguration.getNumber());
        return flights.stream()
                .map(flightRepository::save)
                .collect(Collectors.toList());
    }
}

package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.data.Flight;
import com.lhsystems.module.data.generator.ancillary.data.Route;
import com.lhsystems.module.data.generator.ancillary.data.Tariff;
import com.lhsystems.module.data.generator.ancillary.generator.configuration.FlightGeneratorConfiguration;
import com.lhsystems.module.data.generator.ancillary.generator.core.FlightGenerator;
import com.lhsystems.module.data.generator.ancillary.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class FlightGeneratorStarter {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightGeneratorStarter(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    List<Flight> generateFlightsEntities(long startId, FlightGeneratorConfiguration flightGeneratorConfiguration,
                                         List<Tariff> tariffs, List<Route> routes) {
        FlightGenerator flightGenerator = new FlightGenerator(startId, routes, tariffs,
                        flightGeneratorConfiguration.getMinFlightDateAsLocalDate(),
                        flightGeneratorConfiguration.getMaxFlightDateAsLocalDate());
        List<Flight> flights = flightGenerator.generateList(flightGeneratorConfiguration.getNumber());
        return flights.stream()
                .map(flightRepository::save)
                .collect(Collectors.toList());
    }
}

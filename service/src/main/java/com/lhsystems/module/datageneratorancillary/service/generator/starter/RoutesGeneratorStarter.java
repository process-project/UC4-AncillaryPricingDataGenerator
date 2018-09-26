package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.repository.AirportRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.RouteRepository;
import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Starts generating first airports, then routes entities from ssim file and save them into database.
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
public class RoutesGeneratorStarter {
    /** The patern used for finding ida codes in line. */
    private Pattern idaPattern = Pattern.compile("\\s[A-Z]{3}");

    /** The repository used for saving airports. */
    private final AirportRepository airportRepository;

    /** The repository used for saving routes. */
    private final RouteRepository routeRepository;

    /** Generates a stream of pseudo random numbers used for generating flights.*/
    private final ExtendedRandom random = new ExtendedRandom();

    /**
     * Instantiates a new route generator starer with injected repositories
     *
     * @param airportRepository
     *        repository responsible for crud operations on airport entities
     * @param routeRepository
     *        repository responsible for crud operations on route entities
     */
    @Autowired
    public RoutesGeneratorStarter(AirportRepository airportRepository,
                                  RouteRepository routeRepository) {
        this.airportRepository = airportRepository;
        this.routeRepository = routeRepository;
    }

    /**
     * Iterate through lines and create routes and airports from each line
     *
     * @param markets
     *        list of possible markets to used as airport market
     * @param ssimLines
     *        list of lines from ssim file
     * @return
     *        list of routes
     */
    public List<Route> generateRoutesAndAirports(List<Market> markets, List<String> ssimLines){
        return ssimLines
                .stream()
                .map(e -> generateDate(e, markets))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Find iata codes in ssim line, then generate airports from them and add new route if necessary
     *
     * @param line
     *      one line from ssim file
     * @param markets
     *      list of possible markets to used as airport market
     * @return
     *        route
     */
    private Route generateDate(String line, List<Market> markets) {
        Matcher matcher = idaPattern.matcher(line);
        List<Airport> airports = new ArrayList<>();
        while (matcher.find()) {
            String iataCode = matcher.group(0).trim();
            final Market market = random.getOneRandomElement(markets);
            airports.add(new Airport(iataCode, iataCode, market));
        }
        if(airports.size() != 2) {
            return null;
        }

        airports.forEach(this::saveAirportIfNotExits);

        return getOrCreateRouteIfNotExists(airports.get(0), airports.get(1));
    }

    /**
     * Check that airport is existing in database, if not, save new one
     *
     * @param airport
     *        airport to check
     */
    private void saveAirportIfNotExits(Airport airport) {
        if (!airportRepository.existsById(airport.getIata())) {
            airportRepository.save(airport);
        }
    }

    /**
     * Check that route is existing in database, if not, create new one
     *
     * @param origin
     *        origin airport for route
     * @param destination
     *        destination airport for route
     * @return
     *      route, new or from database
     */
    private Route getOrCreateRouteIfNotExists(Airport origin, Airport destination){
        Route currentRoute = routeRepository.isRouteExists(origin, destination);
        if(Objects.isNull(currentRoute)) {
            Route route = new Route(origin, destination);
            currentRoute = routeRepository.save(route);
        }
        return currentRoute;
    }
}

package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.repository.AirportRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.RouteRepository;
import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Starts generating first airports, then routes entities from ssim file and save them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public final class RoutesGeneratorStarter {
<<<<<<< Upstream, based on lmuGitlab/topic/EUPLSY-85
    /** The patern used for finding ida codes in line. */
    private final Pattern iataPattern = Pattern.compile("\\s[A-Z]{3}");
=======
    /** The pattern used for finding IATA codes in line. */
    private static final Pattern iataPattern = Pattern.compile("\\s[A-Z]{3}");
>>>>>>> c0ba3f7 EUPLSY-85: Add Bookings and respective Generator, Repository as well as  utility classes

    /** The repository used for saving airports. */
    private final AirportRepository airportRepository;

    /** The repository used for saving routes. */
    private final RouteRepository routeRepository;

    /** Generates a stream of pseudo random numbers used for generating flights.*/
    private final ExtendedRandom random = new ExtendedRandom();

    /**
     * Instantiates a new route generator starer with injected repositories.
     *
     * @param airportRepositoryParam
     *        repository responsible for crud operations on airport entities
     * @param routeRepositoryParam
     *        repository responsible for crud operations on route entities
     */
    @Autowired
    public RoutesGeneratorStarter(final AirportRepository airportRepositoryParam,
            final RouteRepository routeRepositoryParam) {
        airportRepository = airportRepositoryParam;
        routeRepository = routeRepositoryParam;
    }

    /**
     * Find iata codes in ssim line, then generate airports from them and add new route if necessary.
     *
     * @param line
     *      one line from ssim file
     * @param markets
     *      list of possible markets to used as airport market
     * @return
     *        route
     */
<<<<<<< Upstream, based on lmuGitlab/topic/EUPLSY-85
    private Route generateDate(final String line, final List<Market> markets) {
=======
    private Route generateRoute(final String line, final List<Market> markets) {
>>>>>>> c0ba3f7 EUPLSY-85: Add Bookings and respective Generator, Repository as well as  utility classes
        final Matcher matcher = iataPattern.matcher(line);
        final List<Airport> airports = new ArrayList<>();
        while (matcher.find()) {
            final String iataCode = matcher.group(0).trim();
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
     * Iterate through lines and create routes and airports from each line.
     *
     * @param markets
     *        list of possible markets to used as airport market
     * @param ssimLines
     *        list of lines from ssim file
     * @return
     *        list of routes
     */
    List<Route> generateRoutesAndAirportEntities(final List<Market> markets, final List<String> ssimLines){
        return ssimLines
                .stream()
                .map(e -> generateRoute(e, markets))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Check that airport is existing in database, if not, save new one.
     *
     * @param airport
     *        airport to check
     */
    private void saveAirportIfNotExits(final Airport airport) {
        if (!airportRepository.exists(airport.getIata())) {
            airportRepository.save(airport);
        }
    }

    /**
     * Check that route is existing in database, if not, create new one.
     *
     * @param origin
     *        origin airport for route
     * @param destination
     *        destination airport for route
     * @return
     *      route, new or from database
     */
    private Route getOrCreateRouteIfNotExists(final Airport origin, final Airport destination){
        Route currentRoute = routeRepository.isRouteExists(origin, destination);
        if(Objects.isNull(currentRoute)) {
            final Route route = new Route(origin, destination);
            currentRoute = routeRepository.save(route);
        }
        return currentRoute;
    }
}

package com.lhsystems.module.data.generator.ancillary;

import com.lhsystems.module.data.generator.ancillary.data.Airport;
import com.lhsystems.module.data.generator.ancillary.data.Market;
import com.lhsystems.module.data.generator.ancillary.data.Route;
import com.lhsystems.module.data.generator.ancillary.repository.AirportRepository;
import com.lhsystems.module.data.generator.ancillary.repository.RouteRepository;
import com.lhsystems.module.data.generator.ancillary.utils.ExtendedRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SSIMFileReader {

    private String SSIM_FILE = "/ssim-mini.ssim";
    private Pattern idaPattern = Pattern.compile("\\s[A-Z]{3}");

    private final AirportRepository airportRepository;
    private final RouteRepository routeRepository;
    private final ExtendedRandom random = new ExtendedRandom();


    @Autowired
    public SSIMFileReader(AirportRepository airportRepository, RouteRepository routeRepository) {
        this.airportRepository = airportRepository;
        this.routeRepository = routeRepository;
    }

    public List<Route> generateRoutesAndAirports(List<Market> markets){
        InputStream in = getClass().getResourceAsStream(SSIM_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        return reader
                .lines()
                .filter(this::isLineStaredWithProperNumber)
                .map(e -> generateDate(e, markets))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

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

    private void saveAirportIfNotExits(Airport airport) {
        if (!airportRepository.existsById(airport.getIata())) {
            airportRepository.save(airport);
        }
    }

    private Route getOrCreateRouteIfNotExists(Airport origin, Airport destination){
        Route currentRoute = routeRepository.isRouteExists(origin, destination);
        if(currentRoute == null) {
            Route route = new Route(origin, destination);
            currentRoute = routeRepository.save(route);
        }
        return currentRoute;
    }

    private boolean isLineStaredWithProperNumber(String line) {
        return !line.matches("([012]).*$");
    }
}

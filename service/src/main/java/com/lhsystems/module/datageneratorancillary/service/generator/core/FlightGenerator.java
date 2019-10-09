package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.FlightConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Class that randomly generates flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class FlightGenerator extends DataGenerator {


    /**
     * Counter to remember which numbers have been used as
     * <code>flightNumber</code> already.
     */
    private long flightNumberCounter;

    /** The last date of the generation interval. */
    private final LocalDate maximumDate;

    /** The maximum number of bookable tariffs for one flight. */
    private final int maximumNumberTariffs;

    /** The first date of the generation interval. */
    private final LocalDate minimumDate;

    /** The minimum number of bookable tariffs for one flight. */
    private final int minimumNumberTariffs;

    /** The tariffs to be used for flight generation. */
    private final List<Tariff> tariffs;

    /**
     * list of <code>routes</code> objects to be used for the generation of
     * flights.
     */
    private final List<Route> routes;


    private Map<Market, List<Tariff>> tariffsOnMarket;

    /**
     * Constructor.
     *
     * @param paramRoutes
     *            the routes to be used for flight generation
     * @param paramTariffs
     *            the tariffs to be used for flight generation
     * @param configuration
     *            the configuration containing limits on number of tariffs as
     *            well as dates
     */
    public FlightGenerator(final List<Route> paramRoutes,
            final List<Tariff> paramTariffs,
            final FlightConfiguration configuration) {
        super();
        tariffs = paramTariffs;
        minimumDate = configuration.getMinimumFlightDateAsLocalDate();
        routes = paramRoutes;
        maximumDate = configuration.getMaximumFlightDateAsLocalDate();
        minimumNumberTariffs = configuration.getMinimumNumberTariffs();
        maximumNumberTariffs = configuration.getMaximumNumberTariffs();
        tariffsOnMarket = createMapTariffs(paramTariffs);


    }

    private Map<Market, List<Tariff>> createMapTariffs(List<Tariff> paramTariffs) {
        Map<Market, List<Tariff>> tariffsOnMarket = new HashMap<>();

        for(Market market: Market.getAllMarkets()) {
            final List<Tariff> tariffs = new ArrayList<>();
            for (final Tariff tariff: paramTariffs){
                if (tariff.getMarket().equals(market)){
                    tariffs.add(tariff);
                }
            }
            tariffsOnMarket.put(market, tariffs);
        }
        return tariffsOnMarket;
    }


    /**
     * Generate a generic list containing a number of objects.
     *
     * @param numberToGenerate
     *            the number of objects to generate
     * @return the list
     */
    public final List<Flight> generateFlights(final long numberToGenerate, final long batchNumber) {
        return LongStream.range(0, numberToGenerate)
                .mapToObj(n -> generate(n, batchNumber))
                .collect(Collectors.toList());
    }

    protected Flight generate(long numberToGenerate, long batchNumber) {
        final long flightNumber = numberToGenerate * batchNumber;
        final LocalDate departureDate = getRandom().getRandomDay(
                minimumDate,
                maximumDate);
        final LocalTime departureTime = getRandom().getRandomDaytime();
        final Route route = getRandom().getOneRandomElement(routes);
        final List<Tariff> chosenTariffs = chooseTariffs(tariffsOnMarket.get(route.getMarket()));
        return new Flight(
                flightNumber,
                LocalDateTime.of(departureDate, departureTime),
                route,
                chosenTariffs);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Flight generate() {
        final Long flightNumber = increaseFlightNumberCounter();
        final LocalDate departureDate = getRandom().getRandomDay(
                minimumDate,
                maximumDate);
        final LocalTime departureTime = getRandom().getRandomDaytime();
        final Route route = getRandom().getOneRandomElement(routes);
        final List<Tariff> chosenTariffs = chooseTariffs(tariffsOnMarket.get(route.getMarket()));
        return new Flight(
                flightNumber,
                LocalDateTime.of(departureDate, departureTime),
                route,
                chosenTariffs);
    }


    /**
     * Choose tariffs from the member list that lie in the desired market.
     *
     * @param market
     *            the market
     * @return a list of tariffs
     */
    private List<Tariff> chooseTariffs(final List<Tariff> tariffsOfMarket) {
        return getRandom().getRandomlyManyElements(
                tariffsOfMarket,
                minimumNumberTariffs,
                maximumNumberTariffs);
    }

    /**
     * Gets the available markets. I.e. all markets that appear in the list of
     * tariffs.
     *
     * @return the available markets
     */
    private List<Market> getAvailableMarkets(){
        final HashSet<Market> marketSet = new HashSet<>();
        for (final Tariff tariff : tariffs) {
            marketSet.add(tariff.getMarket());
        }
        return new ArrayList<>(marketSet);
    }

    /**
     * Returns the <code>flightNumberCounter</code> of this object and
     * increments it.
     *
     * @return <code>this.flightNumberCounter</code>
     */
    private Long increaseFlightNumberCounter() {
        final Long tempHelper = flightNumberCounter;
        this.setFlightNumberCounter(flightNumberCounter + 1);
        return tempHelper;
    }


    /**
     * Sets the <code>flightNumberCounter</code> to a new number given by the
     * parameter.
     *
     * @param paramFlightNumberCounter
     *            the number that <code>flightNumberCounter</code> is set to.
     */
    private void setFlightNumberCounter(final long paramFlightNumberCounter) {
        flightNumberCounter = paramFlightNumberCounter;
    }

}

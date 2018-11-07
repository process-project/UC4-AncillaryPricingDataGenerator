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
import java.util.HashSet;
import java.util.List;

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
    private int flightNumberCounter;

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
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Flight generate() {
        final int flightNumber = increaseFlightNumberCounter();
        final LocalDate departureDate = getRandom().getRandomDay(
                minimumDate,
                maximumDate);
        final LocalTime departureTime = getRandom().getRandomDaytime();
        final Route route = getRandom().getOneRandomElement(routes);
        final List<Tariff> chosenTariffs = chooseTariffs(route.getMarket());
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
    private List<Tariff> chooseTariffs(final Market market) {
        final List<Tariff> tariffsOfMarket = new ArrayList<>();
        for (final Tariff tariff:tariffs){
            if (tariff.getMarket().equals(market)){
                tariffsOfMarket.add(tariff);
            }
        }
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
    private int increaseFlightNumberCounter() {
        final int tempHelper = flightNumberCounter;
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
    private void setFlightNumberCounter(final int paramFlightNumberCounter) {
        flightNumberCounter = paramFlightNumberCounter;
    }

}

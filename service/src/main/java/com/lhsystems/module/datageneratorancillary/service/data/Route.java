package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.Market;

/**
 * A route defining the origin and destination of flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class Route {

    /**
     * IATA code of the destination airport.
     */
    private final Airport destinationAirport;

    /**
     * States if the flight is a domestic, continental, or intercontinental
     * flight.
     */
    private final Market market;

    /**
     * IATA code of the origin airport.
     */
    private final Airport originAirport;

    /**
     * Instantiates a new route.
     *
     * @param paramOriginAirport
     *            the origin airport
     * @param paramDestinationAirport
     *            the destination airport
     */
    public Route(final Airport paramOriginAirport,
            final Airport paramDestinationAirport) {
        originAirport = paramOriginAirport;
        destinationAirport = paramDestinationAirport;
        market = originAirport.getMarket().getMaximumMarket(
                destinationAirport.getMarket());
    }

    /**
     * Returns the destination airport.
     *
     * @return the destination airport
     */
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Returns the market.
     *
     * @return the market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Returns the origin airport.
     *
     * @return the origin airport
     */
    public Airport getOriginAirport() {
        return originAirport;
    }

}

package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A route defining the origin and destination of flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Route")
public final class Route {


    /** The id of the compartment. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * IATA code of the destination airport.
     */
    @OneToOne
    @JoinColumn(name = "DESTINATION_AIRPORT")
    private final Airport destinationAirport;

    /**
     * States if the flight is a domestic, continental, or intercontinental
     * flight.
     */
    @Enumerated(EnumType.STRING)
    private final Market market;

    /**
     * IATA code of the origin airport.
     */
    @OneToOne
    @JoinColumn(name = "ORIGIN_AIRPORT")
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
        market = originAirport.getMarket().getMaximumMarket(destinationAirport.getMarket());
    }

    /**
     * Default Constructor needed for an Entity. Instantiates a new route
     * class.
     */
    public Route() {
        originAirport = null;
        destinationAirport = null;
        market = Market.DOMESTIC;
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

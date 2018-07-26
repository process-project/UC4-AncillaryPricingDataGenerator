package com.lhsystems.module.datageneratorancillary.service;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The class Flight serves as a model for flights.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */

final public class Flight {

    /**
     * Unique identifier to be used in a database.
     */
    private final long id;

    /**
     * Flight number of the flight.
     */
    private final int flightNumber;

    /**
     * Time of day of departure in local time.
     */
    private final LocalTime departureTime;

    /**
     * Date of departure in local time.
     */
    private final LocalDate departureDate;

    /**
     * IATA code of the origin airport.
     */
    private final String originAirport;

    /**
     * IATA code of the destination airport.
     */
    private final String destinationAirport;

    /**
     * States if the flight is a domestic, continental, or intercontinental
     * flight.
     */
    private final Market market;

    /**
     * Constructor.
     *
     * @param id
     *            unique identifier to be used in a database
     * @param flightNumber
     *            flight number of the flight
     * @param departureTime
     *            time of day of departure in utc time
     * @param departureDate
     *            date of departure in utc time
     * @param originAirport
     *            IATA code of the origin airport
     * @param destinationAirport
     *            IATA code of the destination airport
     * @param market
     *            states if the flight is a domestic, continental, or
     *            intercontinental flight
     */
    public Flight(final long id, final Integer flightNumber,
            final LocalTime departureTime, final LocalDate departureDate,
            final String originAirport, final String destinationAirport,
            final Market market) {

        this.id = id;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.market = market;
    }

    /**
     * Returns the <code>departureDate</code> (in local time) of the flight
     * object.
     *
     * @return <code>departureDate</code> of the flight object
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Returns the <code>departureTime</code> (in local time) of the flight
     * object.
     *
     * @return <code>departureTime</code> of the flight object
     */
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the <code>destinationAirport</code> of the flight object.
     *
     * @return <code>destinationAirport</code> of the flight object
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Returns the <code>flightNumber</code> of the flight object.
     *
     * @return <code>flightNumber</code> of the flight object
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the <code>id</code> of the flight object.
     *
     * @return <code>id</code> of the flight object
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the <code>market</code> of the flight object.
     *
     * @return <code>market</code> of the flight object
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Returns the <code>originAirport</code> of the flight object.
     *
     * @return <code>originAirport</code> of the flight object
     */
    public String getOriginAirport() {
        return originAirport;
    }

}

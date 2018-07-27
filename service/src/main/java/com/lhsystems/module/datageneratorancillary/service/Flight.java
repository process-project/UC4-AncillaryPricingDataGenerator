package com.lhsystems.module.datageneratorancillary.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The class Flight serves as a model for flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class Flight {

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
     * @param paramId
     *            unique identifier to be used in a database
     * @param paramFlightNumber
     *            flight number of the flight
     * @param departureDateTime
     *            time of day and day of departure in local time
     * @param paramOriginAirport
     *            IATA code of the origin airport
     * @param paramDestinationAirport
     *            IATA code of the destination airport
     * @param paramMarket
     *            states if the flight is a domestic, continental, or
     *            intercontinental flight
     */
    public Flight(final long paramId, final Integer paramFlightNumber,
            final LocalDateTime departureDateTime,
            final String paramOriginAirport, final String paramDestinationAirport,
            final Market paramMarket) {

        id = paramId;
        flightNumber = paramFlightNumber;
        departureTime = departureDateTime.toLocalTime();
        departureDate = departureDateTime.toLocalDate();
        originAirport = paramOriginAirport;
        this.destinationAirport = paramDestinationAirport;
        this.market = paramMarket;
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

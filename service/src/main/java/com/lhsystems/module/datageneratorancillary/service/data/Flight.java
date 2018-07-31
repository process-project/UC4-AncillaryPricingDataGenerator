/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * The class Flight serves as a model for flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class Flight {

    /** The tariffs bookable on this flight. */
    private final List<Tariff> bookableTariffs;

    /**
     * Date of departure in local time.
     */
    private final LocalDate departureDate;

    /**
     * Time of day of departure in local time.
     */
    private final LocalTime departureTime;

    /**
     * Flight number of the flight.
     */
    private final int flightNumber;

    /**
     * Unique identifier to be used in a database.
     */
    private final long id;

    /** The route of the flight. */
    private final Route route;

    /**
     * Constructor.
     *
     * @param paramId
     *            unique identifier to be used in a database
     * @param paramFlightNumber
     *            flight number of the flight
     * @param departureDateTime
     *            time of day and day of departure in local time
     * @param paramRoute
     *            the route of the flight
     * @param tariffs
     *            the tariffs that are bookable on this flight
     */
    public Flight(final long paramId, final Integer paramFlightNumber,
            final LocalDateTime departureDateTime,
            final Route paramRoute, final List<Tariff> tariffs) {

        id = paramId;
        flightNumber = paramFlightNumber;
        departureTime = departureDateTime.toLocalTime();
        departureDate = departureDateTime.toLocalDate();
        route = paramRoute;
        bookableTariffs = tariffs;
    }

    /**
     * Returns the bookable tariffs of the flight.
     *
     * @return the bookable tariffs
     */
    public List<Tariff> getBookableTariffs() {
        return bookableTariffs;
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
     * Returns the <code>route</code> of the flight.
     *
     * @return the <code>route</code>
     */
    public Route getRoute() {
        return route;
    }
}
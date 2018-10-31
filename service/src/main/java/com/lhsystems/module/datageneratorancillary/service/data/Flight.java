/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The class Flight serves as a model for flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Entity
@Table(name = "Flight")
public final class Flight {

    /** The tariffs bookable on this flight. */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Flights_Tariffs",
    joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "tariff_id", referencedColumnName = "id"))
    private final List<Tariff> bookableTariffs;

    /**
     * Date of departure in local time.
     */
    @Column(name = "DEPARTURE_DATE")
    private final LocalDate departureDate;

    /**
     * Time of day of departure in local time.
     */
    @Column(name = "DEPARTURE_TIME")
    private final LocalTime departureTime;

    /**
     * Flight number of the flight.
     */
    @Column(name = "FLIGHT_NUMBER")
    private final int flightNumber;

    /**
     * Unique identifier to be used in a database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The route of the flight. */
    @OneToOne
    @JoinColumn(name = "ROUTE")
    private final Route route;

    /**
     * Default Constructor needed for an Entity. Instantiates a new flight
     * class.
     */
    public Flight() {
        flightNumber = 0;
        departureTime = null;
        departureDate = null;
        route = null;
        bookableTariffs = null;
    }

    /**
     * Constructor.
     *
     * @param paramFlightNumber
     *            flight number of the flight
     * @param departureDateTime
     *            time of day and day of departure in local time
     * @param paramRoute
     *            the route of the flight
     * @param tariffs
     *            the tariffs that are bookable on this flight
     */
    public Flight(final Integer paramFlightNumber,
            final LocalDateTime departureDateTime,
            final Route paramRoute, final List<Tariff> tariffs) {
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
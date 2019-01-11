/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service.cassandra.data;

import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The class Flight serves as a model for flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Table("flights")
public final class FlightCassandra {

    /** The tariffs bookable on this flight. */
    /*@Column
    private final List<Tariff> bookableTariffs;*/

   /* *//**
     * Date of departure in local time.
     *//*
    @Column("DEPARTURE_DATE")
    private final LocalDate departureDate;

    *//**
     * Time of day of departure in local time.
     *//*
    @Column("DEPARTURE_TIME")
    private final LocalTime departureTime;*/

    /**
     * Flight number of the flight.
     */
    @PrimaryKeyColumn(
            name = "flight_number",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private final int flightNumber;

    /**
     * Unique identifier to be used in a database.
     */
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID id;

   /* *//** The route of the flight. *//*
    @Column
    private final Route route;
*/
    @Column("IATA_DESTINATION_AIRPORT")
    private String iataCodeDestinationAirport;


    @Column("IATA_ORIGIN_AIRPORT")
    private String iataCodeOriginAirport;

    @Column("MARKET")
    private String market;

    /**
     * Default Constructor needed for an Entity. Instantiates a new flight
     * class.
     */
    public FlightCassandra() {
        flightNumber = 0;
        //departureTime = null;
        //departureDate = null;
        id = UUID.randomUUID();
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
    public FlightCassandra(final Integer paramFlightNumber,
                           final LocalDateTime departureDateTime,
                           final Route paramRoute, final List<Tariff> tariffs) {
        flightNumber = paramFlightNumber;
        //departureTime = departureDateTime.toLocalTime();
        //departureDate = LocalDate.fromMillisSinceEpoch(departureDateTime.toEpochSecond(ZoneOffset.UTC));
        id = UUID.randomUUID();
    }


    public FlightCassandra(final Flight flight) {
        flightNumber = flight.getFlightNumber();
        //departureTime = flight.getDepartureTime();
        //departureDate = LocalDate.fromMillisSinceEpoch(flight.getDepartureDate().toEpochDay());
        id = UUID.randomUUID();
        market = flight.getRoute().getMarket().name();
        iataCodeDestinationAirport = flight.getRoute().getDestinationAirport().getIata();
        iataCodeOriginAirport = flight.getRoute().getOriginAirport().getIata();
    }

    /**
     * Returns the <code>departureDate</code> (in local time) of the flight
     * object.
     *
     * @return <code>departureDate</code> of the flight object
     *//*
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    *//**
     * Returns the <code>departureTime</code> (in local time) of the flight
     * object.
     *
     * @return <code>departureTime</code> of the flight object
     *//*
    public LocalTime getDepartureTime() {
        return departureTime;
    }*/

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
    public UUID getId() {
        return id;
    }

    public String getIataCodeDestinationAirport() {
        return iataCodeDestinationAirport;
    }

    public String getIataCodeOriginAirport() {
        return iataCodeOriginAirport;
    }

    public String getMarket() {
        return market;
    }
}
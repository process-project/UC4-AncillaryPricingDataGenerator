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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

/**
 * The class Flight serves as a model for flights.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Table("flight")
public final class FlightCassandra {

    /** The tariffs bookable on this flight. */
    /*@Column
    private final List<Tariff> bookableTariffs;*/

   /**
     * Date of departure in local time.
     */
    @Column("DEPARTURE_DATE")
    private final LocalDate departureDate;

    /**
     * Time of day of departure in local time.
     */
    @Column("DEPARTURE_TIME")
    private final LocalTime departureTime;

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
        departureTime = null;
        departureDate = null;
        id = UUID.randomUUID();
    }

    public FlightCassandra(final LocalDateTime departureDateTimeParam,
                           final int flightNumberParam,
                           final UUID idParam,
                           final String iataCodeDestinationAirportParam,
                           final String iataCodeOriginAirportParam,
                           final String marketParam) {
        departureDate = departureDateTimeParam.toLocalDate();
        departureTime = departureDateTimeParam.toLocalTime();
        flightNumber = flightNumberParam;
        iataCodeDestinationAirport = iataCodeDestinationAirportParam;
        iataCodeOriginAirport = iataCodeOriginAirportParam;
        market = marketParam;
        id = idParam;
    }

    public FlightCassandra(final Flight flight) {
        flightNumber = flight.getFlightNumber();
        departureDate = flight.getDepartureDate();
        departureTime = flight.getDepartureTime();
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
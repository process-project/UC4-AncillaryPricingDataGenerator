package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Data structure describing a booking without ancillaries.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Product")
public final class SimpleBooking {

    /**
     * The number of days between the date of booking and the day of departure.
     */
    @Column(name = "DAYS_BEFORE_DEPARTURE")
    private final int daysBeforeDeparture;

    /** The booked flight. */
    @OneToOne
    @JoinColumn(name = "FLIGHT")
    private final Flight flight;

    /** The id of this booking. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The number of passengers. */
    @Column(name = "NUMBER_PASSENGERS")
    private final int numberPassengers;

    /** The chosen tariff. */
    @OneToOne
    @JoinColumn(name = "TARIFF")
    private final Tariff tariff;

    /**
     * Instantiates a new simple booking. Default constructor needed for entity.
     */
    public SimpleBooking() {
        daysBeforeDeparture = 0;
        flight = null;
        numberPassengers = 0;
        tariff = null;
    }
    /**
     * Instantiates a new simple booking.
     *
     * @param paramDaysBeforeDeparture
     *            The number of days between the date of booking and the day of
     *            departure.
     * @param paramFlight
     *            the booked flight
     * @param paramNumberPassengers
     *            the number of passengers
     * @param paramTariff
     *            the chosen tariff
     */
    public SimpleBooking(final int paramDaysBeforeDeparture,
            final Flight paramFlight,
            final int paramNumberPassengers, final Tariff paramTariff) {
        daysBeforeDeparture = paramDaysBeforeDeparture;
        flight = paramFlight;
        numberPassengers = paramNumberPassengers;
        tariff = paramTariff;
    }
}

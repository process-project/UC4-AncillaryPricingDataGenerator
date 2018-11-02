package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Describes the order of a service for a Booking.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "ServiceOrder")
public final class ServiceOrder {

    /** The booking this service order belongs to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    /** The number of times this service is booked. */
    @Column(name = "COUNT")
    private final int count;

    /** The time of the order in days before departure. */
    @Column(name = "DAYS_BEFORE_DEPARTURE")
    private final int daysBeforeDeparture;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The price of the order. */
    @Column(name = "PRICE")
    private final double price;

    /** The service that is booked. */
    @OneToOne
    @JoinColumn(name = "SERVICE")
    private final Service service;

    /**
     * Instantiates a new service order.
     *
     * @param paramService
     *            the service
     * @param paramCount
     *            the count
     * @param paramPrice
     *            the price
     * @param paramDaysBeforeDeparture
     *            the days before departure
     */
    public ServiceOrder(final Service paramService, final int paramCount,
            final double paramPrice, final int paramDaysBeforeDeparture) {
        service = paramService;
        count = paramCount;
        price = paramPrice;
        daysBeforeDeparture = paramDaysBeforeDeparture;
    }

    /**
     * Gets the booking.
     *
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the days before departure.
     *
     * @return the days before departure
     */
    public int getDaysBeforeDeparture() {
        return daysBeforeDeparture;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the service.
     *
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * Sets the booking.
     *
     * @param booking
     *            the new booking
     */
    public void setBooking(final Booking booking) {
        this.booking = booking;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final long id) {
        this.id = id;
    }
}

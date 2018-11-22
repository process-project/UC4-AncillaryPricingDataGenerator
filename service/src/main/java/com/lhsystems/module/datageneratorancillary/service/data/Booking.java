package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * a booking including seat and baggage selection.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Booking")
public final class Booking {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The core booking. */
    @OneToOne
    @JoinColumn(name = "CORE_BOOKING")
    private final CoreBooking coreBooking;


    /** The service orders. */
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private final List<ServiceOrder> serviceOrders;

    /**
     * Instantiates a new complete booking. DefaultConstructor needed for
     * entity.
     */
    public Booking() {
        coreBooking = null;
        serviceOrders = null;
    }

    /**
     * Instantiates a new complete booking.
     *
     * @param paramCoreBooking
     *            the core booking field value
     * @param paramServiceOrders
     *            the service orders of this booking
     */
    public Booking(
            final CoreBooking paramCoreBooking,
            final List<ServiceOrder> paramServiceOrders) {
        coreBooking = paramCoreBooking;
        serviceOrders = paramServiceOrders;
    }

    /**
     * Gets the core booking.
     *
     * @return the core booking
     */
    public CoreBooking getCoreBooking() {
        return coreBooking;
    }

}

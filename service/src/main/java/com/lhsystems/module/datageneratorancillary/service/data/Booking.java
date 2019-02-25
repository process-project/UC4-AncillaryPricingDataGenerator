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

    /** The customer who books. */
    @OneToOne
    @JoinColumn(name = "CUSTOMER")
    private final Customer customer;


    /** The service orders. */
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOKING_ID")
    private final List<ServiceOrder> serviceOrders;

    /**
     * Instantiates a new booking. Default constructor needed for entity.
     */
    public Booking() {
        customer = null;
        coreBooking = null;
        serviceOrders = null;
    }

    /**
     * Instantiates a new booking.
     *
     * @param paramCoreBooking
     *            the core booking field value
     * @param paramCustomer
     *            the customer field value
     * @param paramServiceOrders
     *            the service orders of this booking
     */
    public Booking(
            final CoreBooking paramCoreBooking,
            final Customer paramCustomer,
            final List<ServiceOrder> paramServiceOrders) {
        coreBooking = paramCoreBooking;
        customer = paramCustomer;
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


    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }
}

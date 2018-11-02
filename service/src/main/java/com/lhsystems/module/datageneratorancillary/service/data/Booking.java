package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    /** The service selection. */
    @OneToOne
    @JoinColumn(name = "SERVICE_SELECTION")
    private final ServiceSelection serviceSelection;

    /**
     * Instantiates a new complete booking. DefaultConstructor needed for
     * entity.
     */
    public Booking() {
        coreBooking = null;
        serviceSelection = null;
    }

    /**
     * Instantiates a new complete booking.
     *
     * @param paramCoreBooking
     *            the core booking field value
     * @param paramServiceSelection
     *            the service selection field value
     */
    public Booking(
            final CoreBooking paramCoreBooking,
            final ServiceSelection paramServiceSelection) {
        coreBooking = paramCoreBooking;
        serviceSelection = paramServiceSelection;
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
     * Gets the service selection.
     *
     * @return the service selection
     */
    public ServiceSelection getServiceSelection() {
        return serviceSelection;
    }

}

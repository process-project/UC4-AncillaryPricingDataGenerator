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

    /** The seat selection. */
    @OneToOne
    @JoinColumn(name = "SEAT_SELECTION")
    private final SeatSelection seatSelection;

    /** The baggage selection. */
    @OneToOne
    @JoinColumn(name = "BAGGAGE_SELECTION")
    private final BaggageSelection baggageSelection;

    /**
     * Instantiates a new complete booking. DefaultConstructor needed for
     * entity.
     */
    public Booking() {
        coreBooking = null;
        seatSelection = null;
        baggageSelection = null;
    }

    /**
     * Instantiates a new complete booking.
     *
     * @param paramCoreBooking
     *            the simple booking field value
     * @param paramSeatSelection
     *            the seat selection field value
     * @param paramBaggageSelection
     *            the baggage selection field value
     */
    public Booking(
            final CoreBooking paramCoreBooking,
            final SeatSelection paramSeatSelection,
            final BaggageSelection paramBaggageSelection) {
        coreBooking = paramCoreBooking;
        seatSelection = paramSeatSelection;
        baggageSelection = paramBaggageSelection;
    }

    /**
     * Gets the simple booking.
     *
     * @return the simple booking
     */
    public CoreBooking getCoreBooking() {
        return coreBooking;
    }

    /**
     * Gets the seat selection.
     *
     * @return the seat selection
     */
    public SeatSelection getSeatSelection() {
        return seatSelection;
    }

    /**
     * Gets the baggage selection.
     *
     * @return the baggage selection
     */
    public BaggageSelection getBaggageSelection() {
        return baggageSelection;
    }

}

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
@Table(name = "CompleteBooking")
public final class CompleteBooking {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The simple booking. */
    @OneToOne
    @JoinColumn(name = "SIMPLE_BOOKING")
    private final SimpleBooking simpleBooking;

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
    public CompleteBooking() {
        simpleBooking = null;
        seatSelection = null;
        baggageSelection = null;
    }

    /**
     * Instantiates a new complete booking.
     *
     * @param paramSimpleBooking
     *            the simple booking field value
     * @param paramSeatSelection
     *            the seat selection field value
     * @param paramBaggageSelection
     *            the baggage selection field value
     */
    public CompleteBooking(
            final SimpleBooking paramSimpleBooking,
            final SeatSelection paramSeatSelection,
            final BaggageSelection paramBaggageSelection) {
        simpleBooking = paramSimpleBooking;
        seatSelection = paramSeatSelection;
        baggageSelection = paramBaggageSelection;
    }

    /**
     * Gets the simple booking.
     *
     * @return the simple booking
     */
    public SimpleBooking getSimpleBooking() {
        return simpleBooking;
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

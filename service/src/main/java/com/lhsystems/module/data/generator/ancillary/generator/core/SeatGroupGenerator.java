package com.lhsystems.module.data.generator.ancillary.generator.core;

import com.lhsystems.module.data.generator.ancillary.data.SeatGroup;

/**
 * Generates seat groups randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatGroupGenerator extends DataGenerator {

    /** The maximal number of seats a seat group generated can have. */
    private static final int MAX_NUMBER_SEATS = 20;

    /** The maximal seat price a seat group generated can have. */
    private static final double MAX_SEAT_PRICE = 30;

    /** The minimal number of seats a seat group generated can have. */
    private static final int MIN_NUMBER_SEATS = 6;

    /** The minimal seat price a seat group generated can have. */
    private static final double MIN_SEAT_PRICE = 8;

    /**
     * Instantiates a new seat group generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     */
    public SeatGroupGenerator(final Long startId) {
        super(startId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SeatGroup generate(final long id) {
        final int numberSeats = getRandom().nextInt(
                MIN_NUMBER_SEATS,
                MAX_NUMBER_SEATS);
        final double seatPrice = getRandom().getRandomRoundedDouble(
                MIN_SEAT_PRICE,
                MAX_SEAT_PRICE,
                2);
        final String name = Integer.toString(numberSeats)
                + "s"
                + Double.toString(seatPrice);
        return new SeatGroup(id, name, numberSeats, seatPrice);
    }
}

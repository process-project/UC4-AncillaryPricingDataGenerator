package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatGroupConfiguration;

/**
 * Generates seat groups randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatGroupGenerator extends DataGenerator {

    /** The maximal number of seats a generated seat group can have. */
    private final int maxNumberSeats;

    /** The maximal seat price a generated seat group can have. */
    private final double maxSeatPrice;

    /** The minimal number of seats a generated seat group can have. */
    private final int minNumberSeats;

    /** The minimal seat price a generated seat group can have. */
    private final double minSeatPrice;

    /**
     * Instantiates a new seat group generator.
     *
     * @param seatGroupConfiguration
     *            the seat group configuration
     */
    public SeatGroupGenerator(
            final SeatGroupConfiguration seatGroupConfiguration) {
        maxNumberSeats = seatGroupConfiguration.getMaximumNumberSeats();
        maxSeatPrice = seatGroupConfiguration.getMaximumSeatPrice();
        minNumberSeats = seatGroupConfiguration.getMinimumNumberSeats();
        minSeatPrice = seatGroupConfiguration.getMinimumSeatPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SeatGroup generate() {
        final int numberSeats = getRandom().nextInt(
                minNumberSeats,
                maxNumberSeats);
        final double seatPrice = getRandom().getRandomRoundedDouble(
                minSeatPrice,
                maxSeatPrice,
                2);
        final String name = Integer.toString(numberSeats)
                + "s"
                + Double.toString(seatPrice);
        return new SeatGroup(name, numberSeats, seatPrice);
    }
}

package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.SeatingModelConfiguration;

import java.util.List;

/**
 * Generates seating models randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatingModelGenerator extends DataGenerator {

    /** The maximal number of seat groups in a model. */
    private final int maxNumberSeatGroups;

    /** The minimal number of seat groups in a model. */
    private final int minNumberSeatGroups;

    /**
     * The seat groups from which the seat groups of a random seating model are
     * chosen.
     */
    private final List<SeatGroup> seatGroups;

    /**
     * Instantiates a new seating model generator.
     *
     * @param paramSeatGroups
     *            the seat groups from which we chose
     * @param seatingModelConfiguration
     *            the seating model configuration
     */
    public SeatingModelGenerator(
            final List<SeatGroup> paramSeatGroups,
            final SeatingModelConfiguration seatingModelConfiguration) {
        seatGroups = paramSeatGroups;
        maxNumberSeatGroups = seatingModelConfiguration.getMaximumNumberSeatGroups();
        minNumberSeatGroups = seatingModelConfiguration.getMinimumNumberSeatGroups();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected SeatingModel generate() {
        final List<SeatGroup> chosenSeatGroups = getRandom().getRandomlyManyElements(
                seatGroups,
                minNumberSeatGroups,
                maxNumberSeatGroups);
        return new SeatingModel(chosenSeatGroups);
    }


}
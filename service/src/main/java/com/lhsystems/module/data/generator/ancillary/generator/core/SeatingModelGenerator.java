package com.lhsystems.module.data.generator.ancillary.generator.core;

import com.lhsystems.module.data.generator.ancillary.data.SeatGroup;
import com.lhsystems.module.data.generator.ancillary.data.SeatingModel;

import java.util.List;

/**
 * Generates seating models randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatingModelGenerator extends DataGenerator {

    /** The maximal number of seat groups in a model. */
    private static final int MAX_NUMBER_SEAT_GROUPS = 4;

    /** The minimal number of seat groups in a model. */
    private static final int MIN_NUMBER_SEAT_GROUPS = 1;

    /**
     * The seat groups from which the seat groups of a random seating model are
     * chosen.
     */
    private final List<SeatGroup> seatGroups;

    /**
     * Instantiates a new seating model generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     * @param paramSeatGroups
     *            the seat groups from which we chose
     */
    public SeatingModelGenerator(final Long startId,
            final List<SeatGroup> paramSeatGroups) {
        super(startId);
        seatGroups = paramSeatGroups;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected SeatingModel generate(final long id) {
        final List<SeatGroup> chosenSeatGroups = getRandom().getRandomlyManyElements(
                seatGroups,
                MIN_NUMBER_SEAT_GROUPS,
                MAX_NUMBER_SEAT_GROUPS);
        return new SeatingModel(id, chosenSeatGroups);
    }


}
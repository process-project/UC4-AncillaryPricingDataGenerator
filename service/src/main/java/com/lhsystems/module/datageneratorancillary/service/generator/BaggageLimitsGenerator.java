package com.lhsystems.module.datageneratorancillary.service.generator;

import com.lhsystems.module.datageneratorancillary.service.ExtendedRandom;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;

/**
 * Generates baggage limits randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageLimitsGenerator extends DataGenerator {

    /** The maximum max circumference of a baggage limits object. */
    private static double MAXIMUM_CIRCUMFERENCE = 165;

    /** The maximum count of a baggage limits object. */
    private static int MAXIMUM_COUNT = 5;

    /** The maximum max length for any side of a piece of baggage. */
    private static double MAXIMUM_SIDE_LENGTH = 70;

    /** The maximum weight of a baggage limits object. */
    private static double MAXIMUM_WEIGHT = 40;

    /** The minimum max circumference of a baggage limits object. */
    private static double MINIMUM_CIRCUMFERENCE = 30;

    /** The minimum count of a baggage limits object. */
    private static int MINIMUM_COUNT = 1;

    /** The minimum max length for any side of a piece of baggage. */
    private static double MINIMUM_SIDE_LENGTH = 5;

    /** The minimum weight of a baggage limits object. */
    private static double MINIMUM_WEIGHT = 15;

    /**
     * Instantiates a new baggage limits generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     * @param paramRandom
     *            the random number generator
     */
    public BaggageLimitsGenerator(final Long startId,
            final ExtendedRandom paramRandom) {
        super(startId, paramRandom);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggageLimits generate(final long id) {
        final double widthMax = getRandom().getRandomRoundedDouble(
                MINIMUM_SIDE_LENGTH,
                MAXIMUM_SIDE_LENGTH,
                1);
        final double lengthMax = getRandom().getRandomRoundedDouble(
                MINIMUM_SIDE_LENGTH,
                MAXIMUM_SIDE_LENGTH,
                1);
        final double heightMax = getRandom().getRandomRoundedDouble(
                MINIMUM_SIDE_LENGTH,
                MAXIMUM_SIDE_LENGTH,
                1);
        final int countMax = getRandom().nextInt(MINIMUM_COUNT, MAXIMUM_COUNT);
        final double weightMax = getRandom().getRandomRoundedDouble(
                MINIMUM_WEIGHT,
                MAXIMUM_WEIGHT,
                1);
        final double circumferenceMax = getRandom().getRandomRoundedDouble(
                MINIMUM_CIRCUMFERENCE,
                MAXIMUM_CIRCUMFERENCE,
                1);
        return new BaggageLimits(
                id,
                new BaggageSize(
                        circumferenceMax,
                        heightMax,
                        lengthMax,
                        widthMax),
                countMax,
                weightMax);
    }

}

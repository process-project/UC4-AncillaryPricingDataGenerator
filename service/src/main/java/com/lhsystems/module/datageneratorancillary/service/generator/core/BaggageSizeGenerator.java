package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;

/**
 * Generates baggage sizes randomly.
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class BaggageSizeGenerator extends DataGenerator {

    /** The maximum max circumference of a baggage limits object. */
    private static double MAXIMUM_CIRCUMFERENCE = 165;

    /** The maximum max length for any side of a piece of baggage. */
    private static double MAXIMUM_SIDE_LENGTH = 70;

    /** The minimum max circumference of a baggage limits object. */
    private static double MINIMUM_CIRCUMFERENCE = 30;

    /** The minimum max length for any side of a piece of baggage. */
    private static double MINIMUM_SIDE_LENGTH = 5;

    /**
     * Instantiates a new baggage size generator.
     *
     * 
     */
    public BaggageSizeGenerator() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object generate() {
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
        final double circumferenceMax = getRandom().getRandomRoundedDouble(
                MINIMUM_CIRCUMFERENCE,
                MAXIMUM_CIRCUMFERENCE,
                1);
        return new BaggageSize(
                circumferenceMax,
                heightMax,
                lengthMax,
                widthMax);
    }

}

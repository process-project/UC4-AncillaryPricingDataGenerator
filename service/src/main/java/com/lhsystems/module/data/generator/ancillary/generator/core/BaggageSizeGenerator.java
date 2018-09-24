package com.lhsystems.module.data.generator.ancillary.generator.core;

import com.lhsystems.module.data.generator.ancillary.data.BaggageSize;

/**
 * Generates baggage sizes randomly.
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
     * @param startId
     *            the start id
     */
    public BaggageSizeGenerator(final Long startId) {
        super(startId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object generate(final long startId) {
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

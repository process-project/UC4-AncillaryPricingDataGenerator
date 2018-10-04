package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageSizeConfiguration;

/**
 * Generates baggage sizes randomly.
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class BaggageSizeGenerator extends DataGenerator {

    /** The minimum side length. */
    private final double minimumSideLength;

    /** The maximum side length. */
    private final double maximumSideLength;

    /** The minimum circumference. */
    private final double minimumCircumference;

    /** The maximum circumference. */
    private final double maximumCircumference;

    /**
     * Instantiates a new baggage size generator.
     *
     * @param baggageSizeConfiguration
     *            the baggage size configuration
     */
    public BaggageSizeGenerator(
            final BaggageSizeConfiguration baggageSizeConfiguration) {
        minimumCircumference = baggageSizeConfiguration.getMinimumCircumference();
        maximumCircumference = baggageSizeConfiguration.getMaximumCircumference();
        minimumSideLength = baggageSizeConfiguration.getMinimumSideLength();
        maximumSideLength = baggageSizeConfiguration.getMaximumSideLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object generate() {
        final double widthMax = getRandom().getRandomRoundedDouble(
                minimumSideLength,
                maximumSideLength,
                1);
        final double lengthMax = getRandom().getRandomRoundedDouble(
                minimumSideLength,
                maximumSideLength,
                1);
        final double heightMax = getRandom().getRandomRoundedDouble(
                minimumSideLength,
                maximumSideLength,
                1);
        final double circumferenceMax = getRandom().getRandomRoundedDouble(
                minimumCircumference,
                maximumCircumference,
                1);
        return new BaggageSize(
                circumferenceMax,
                heightMax,
                lengthMax,
                widthMax);
    }

}

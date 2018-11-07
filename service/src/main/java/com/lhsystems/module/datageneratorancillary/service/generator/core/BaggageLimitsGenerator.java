package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageLimitsConfiguration;

import java.util.List;

/**
 * Generates baggage limits randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageLimitsGenerator extends DataGenerator {

    /** The maximum count of bags. */
    private final int maximumCount;

    /** The maximum weight of bags. */
    private final double maximumWeight;

    /** The minimum count of bags. */
    private final int minimumCount;

    /** The minimum weight of bags. */
    private final double minimumWeight;

    /** The baggage sizes of which on is chosen during generation. */
    private final List<BaggageSize> baggageSizes;

    /**
     * Instantiates a new baggage limits generator.
     *
     * @param paramBaggageSizes
     *            baggage sizes used for generation
     * @param baggageLimitsConfiguration
     *            the baggage limits configuration
     */
    public BaggageLimitsGenerator(final List<BaggageSize> paramBaggageSizes,
            final BaggageLimitsConfiguration baggageLimitsConfiguration) {
        baggageSizes = paramBaggageSizes;
        maximumCount = baggageLimitsConfiguration.getMaximumCount();
        minimumCount = baggageLimitsConfiguration.getMinimumCount();
        maximumWeight = baggageLimitsConfiguration.getMaximumWeight();
        minimumWeight = baggageLimitsConfiguration.getMinimumWeight();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggageLimits generate() {

        final int countMax = getRandom().nextInt(minimumCount, maximumCount);
        final double weightMax = getRandom().getRandomRoundedDouble(
                minimumWeight,
                maximumWeight,
                1);
        final BaggageSize baggageSize = getRandom().getOneRandomElement(
                baggageSizes);
        return new BaggageLimits(
                baggageSize,
                countMax,
                weightMax);
    }

}

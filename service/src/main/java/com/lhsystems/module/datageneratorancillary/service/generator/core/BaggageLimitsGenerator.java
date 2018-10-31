package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;

import java.util.List;

/**
 * Generates baggage limits randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageLimitsGenerator extends DataGenerator {

    /** The maximum count of a baggage limits object. */
    private static int MAXIMUM_COUNT = 5;

    /** The maximum weight of a baggage limits object. */
    private static double MAXIMUM_WEIGHT = 40;

    /** The minimum count of a baggage limits object. */
    private static int MINIMUM_COUNT = 1;

    /** The minimum weight of a baggage limits object. */
    private static double MINIMUM_WEIGHT = 15;

    /** The baggage sizes of which on is chosen during generation. */
    private final List<BaggageSize> baggageSizes;

    /**
     * Instantiates a new baggage limits generator.
     *
     * @param paramBaggageSizes
     *            baggage sizes used for generation
     */
    public BaggageLimitsGenerator(final List<BaggageSize> paramBaggageSizes) {
        super();
        baggageSizes = paramBaggageSizes;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggageLimits generate() {

        final int countMax = getRandom().nextInt(MINIMUM_COUNT, MAXIMUM_COUNT);
        final double weightMax = getRandom().getRandomRoundedDouble(
                MINIMUM_WEIGHT,
                MAXIMUM_WEIGHT,
                1);
        final BaggageSize baggageSize = getRandom().getOneRandomElement(
                baggageSizes);
        return new BaggageLimits(
                baggageSize,
                countMax,
                weightMax);
    }

}

package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggagePricingConfiguration;

/**
 * Generates baggage prices randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggagePricingGenerator extends DataGenerator {

    /** The number of decimal places of the prices. */
    private static final int ACCURACY = 2;

    /** The minimum first price. */
    private final double minFirstPrice;

    /** The maximum first price. */
    private final double maxFirstPrice;

    /** The minimum second price. */
    private final double minSecondPrice;

    /** The maximum second price. */
    private final double maxSecondPrice;

    /** The minimum additional price. */
    private final double minAdditionalPrice;

    /** The maximum additional price. */
    private final double maxAdditionalPrice;

    /**
     * Instantiates a new baggage pricing generator.
     *
     * @param baggagePricingConfiguration
     *            the baggage pricing configuration
     */
    public BaggagePricingGenerator(
            final BaggagePricingConfiguration baggagePricingConfiguration) {
        minFirstPrice = baggagePricingConfiguration.getMinimumFirstPrice();
        maxFirstPrice = baggagePricingConfiguration.getMaximumFirstPrice();
        minSecondPrice = baggagePricingConfiguration.getMinimumSecondPrice();
        maxSecondPrice = baggagePricingConfiguration.getMaximumSecondPrice();
        minAdditionalPrice = baggagePricingConfiguration.getMinimumAdditionalPrice();
        maxAdditionalPrice = baggagePricingConfiguration.getMaximumAdditionalPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggagePricing generate() {
        final double firstPrice = getRandom().getRandomRoundedDouble(
                minFirstPrice,
                maxFirstPrice,
                ACCURACY);
        final double secondPrice = getRandom().getRandomRoundedDouble(
                minSecondPrice,
                maxSecondPrice,
                ACCURACY);
        final double additionalPrice = getRandom().getRandomRoundedDouble(
                minAdditionalPrice,
                maxAdditionalPrice,
                ACCURACY);
        return new BaggagePricing(firstPrice, secondPrice, additionalPrice);
    }

}

package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;

/**
 * Generates baggage prices randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggagePricingGenerator extends DataGenerator {

    /** The number of decimal places of the prices. */
    private static final int ACCURACY = 2;

    /** The maximal price for each additional bag. */
    private static final int MAX_ADDITIONAL_PRICE = 125;

    /** The maximal first price. */
    private static final int MAX_FIRST_PRICE = 30;

    /** The maximal second price. */
    private static final int MAX_SECOND_PRICE = 50;

    /** The minimal price for each additional bag. */
    private static final int MIN_ADDITIONAL_PRICE = 15;

    /** The minimal first price. */
    private static final int MIN_FIRST_PRICE = 5;

    /** The maximal second price. */
    private static final int MIN_SECOND_PRICE = 10;

    /**
     * Instantiates a new baggage pricing generator.
     *
     */
    public BaggagePricingGenerator() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggagePricing generate() {
        final double firstPrice = getRandom().getRandomRoundedDouble(
                MIN_FIRST_PRICE,
                MAX_FIRST_PRICE,
                ACCURACY);
        final double secondPrice = getRandom().getRandomRoundedDouble(
                MIN_SECOND_PRICE,
                MAX_SECOND_PRICE,
                ACCURACY);
        final double additionalPrice = getRandom().getRandomRoundedDouble(
                MIN_ADDITIONAL_PRICE,
                MAX_ADDITIONAL_PRICE,
                ACCURACY);
        return new BaggagePricing(firstPrice, secondPrice, additionalPrice);
    }

}

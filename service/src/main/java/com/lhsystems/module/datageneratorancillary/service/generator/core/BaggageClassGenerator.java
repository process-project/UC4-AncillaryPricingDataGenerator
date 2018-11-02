package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;

import java.util.List;

/**
 * Generates baggage classes randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageClassGenerator extends DataGenerator {

    /**
     * The list of baggage limits from which one is chosen when generating a
     * baggage class.
     */
    private final List<BaggageLimits> baggageLimits;

    /**
     * The baggage pricing models from which one is chosen when generating a
     * baggage class.
     */
    private final List<BaggagePricing> baggagePricingModels;

    /**
     * Instantiates a new baggage class generator.
     *
     * @param paramBaggageLimits
     *            the param baggage limits
     * @param paramBaggagePricingModels
     *            the param baggage pricing models
     */
    public BaggageClassGenerator(
            final List<BaggageLimits> paramBaggageLimits,
            final List<BaggagePricing> paramBaggagePricingModels) {
        super();
        baggageLimits = paramBaggageLimits;
        baggagePricingModels = paramBaggagePricingModels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggageClass generate() {
        final BaggageLimits limits = getRandom().getOneRandomElement(baggageLimits);
        final BaggagePricing pricing = getRandom().getOneRandomElement(
                baggagePricingModels);

        final String nameBuilder = (Double.toString(limits.getWeightMax()) + "kg_") +
                "f" + Double.toString(pricing.getFirstPrice()) +
                "s" + Double.toString(pricing.getSecondPrice()) +
                "a" + Double.toString(pricing.getAdditionalPrice());
        return new BaggageClass(
                nameBuilder,
                Integer.MAX_VALUE,
                limits,
                pricing);
    }

}

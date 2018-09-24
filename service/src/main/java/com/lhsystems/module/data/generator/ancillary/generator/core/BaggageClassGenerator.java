package com.lhsystems.module.data.generator.ancillary.generator.core;

import com.lhsystems.module.data.generator.ancillary.data.BaggageClass;
import com.lhsystems.module.data.generator.ancillary.data.BaggageLimits;
import com.lhsystems.module.data.generator.ancillary.data.BaggagePricing;

import java.util.List;

/**
 * Generates baggage classes randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageClassGenerator extends DataGenerator {

    /** The baggage limits from which one is chosen per baggage class. */
    private final List<BaggageLimits> baggageLimits;

    /**
     * The baggage pricing models from which one is chosen per baggage class.
     */
    private final List<BaggagePricing> baggagePricingModels;

    /**
     * Instantiates a new baggage class generator.
     *
     * @param startId
     *            the start id
     * @param paramBaggageLimits
     *            the param baggage limits
     * @param paramBaggagePricingModels
     *            the param baggage pricing models
     */
    public BaggageClassGenerator(final Long startId,
            final List<BaggageLimits> paramBaggageLimits,
            final List<BaggagePricing> paramBaggagePricingModels) {
        super(startId);
        baggageLimits = paramBaggageLimits;
        baggagePricingModels = paramBaggagePricingModels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BaggageClass generate(final long id) {
        final BaggageLimits limits = getRandom().getOneRandomElement(baggageLimits);
        final BaggagePricing pricing = getRandom().getOneRandomElement(
                baggagePricingModels);

        String nameBuilder = (Double.toString(limits.getWeightMax()) + "kg_") +
                "f" + Double.toString(pricing.getFirstPrice()) +
                "s" + Double.toString(pricing.getSecondPrice()) +
                "a" + Double.toString(pricing.getAdditionalPrice());
        return new BaggageClass(id, nameBuilder, limits, pricing);
    }

}

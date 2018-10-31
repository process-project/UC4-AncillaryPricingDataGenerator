package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageGeneratorConfiguration {

    /** Number of how many baggage sizes should be generated. */
    private int baggageSize;

    /** Number of how many baggage limits should be generated. */
    private int baggageLimits;

    /** Number of how many baggage pricing should be generated. */
    private int baggagePricing;

    /** Number of how many baggage class should be generated. */
    private int baggageClass;

    /**
     * Instantiates a new baggage configuration object.
     */
    public BaggageGeneratorConfiguration() {
    }

    /**
     * Gets the size of baggage sizes.
     * @return
     *      baggage size
     */
    public int getBaggageSize() {
        return baggageSize;
    }

    /**
     * Set the size of baggage size, used for reading yml file.
     *
     * @param baggageSizeParam
     *        baggageSize from zml file
     */
    public void setBaggageSize(final int baggageSizeParam) {
        baggageSize = baggageSizeParam;
    }

    /**
     * Gets the size of baggage limits.
     * @return
     *      baggage limits
     */
    public int getBaggageLimits() {
        return baggageLimits;
    }

    /**
     * Set the size of baggage limits, used for reading yml file.
     *
     * @param baggageLimitsParam
     *        baggageLimits from zml file
     */
    public void setBaggageLimits(final int baggageLimitsParam) {
        baggageLimits = baggageLimitsParam;
    }

    /**
     * Gets the size of baggage pricing.
     * @return
     *      baggage pricing
     */
    public int getBaggagePricing() {
        return baggagePricing;
    }

    /**
     * Set the size of baggage pricing, used for reading yml file.
     *
     * @param baggagePricingParam
     *        baggagePricing from zml file
     */
    public void setBaggagePricing(final int baggagePricingParam) {
        baggagePricing = baggagePricingParam;
    }

    /**
     * Gets the size of baggage classes.
     * @return
     *      baggage classes
     */
    public int getBaggageClass() {
        return baggageClass;
    }

    /**
     * Set the size of baggage class, used for reading yml file.
     *
     * @param baggageClassParam
     *        baggageClass from zml file
     */
    public void setBaggageClass(final int baggageClassParam) {
        baggageClass = baggageClassParam;
    }
}

package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class BaggageGeneratorConfiguration {

    /** Number of how many baggage sizes should be generated. */
    private int baggageSize;

    /** Number of how many baggage limits should be generated. */
    private int baggageLimits;

    /** Number of how many baggage pricing should be generated. */
    private int baggagePricing;

    /** Number of how many baggage class should be generated. */
    private int baggageClass;

    /**
     * Gets the size of baggage sizes.
     * @return
     *      baggage size
     */
    public int getBaggageSize() {
        return baggageSize;
    }

    /**
     * Set the size of baggage size, used for reading yml file
     *
     * @param baggageSize
     *        baggageSize from zml file
     */
    public void setBaggageSize(int baggageSize) {
        this.baggageSize = baggageSize;
    }

    /**
     * Gets the size of baggage limits .
     * @return
     *      baggage limits
     */
    public int getBaggageLimits() {
        return baggageLimits;
    }

    /**
     * Set the size of baggage limits, used for reading yml file
     *
     * @param baggageLimits
     *        baggageLimits from zml file
     */
    public void setBaggageLimits(int baggageLimits) {
        this.baggageLimits = baggageLimits;
    }

    /**
     * Gets the size of baggage pricing .
     * @return
     *      baggage pricing
     */
    public int getBaggagePricing() {
        return baggagePricing;
    }

    /**
     * Set the size of baggage pricing, used for reading yml file
     *
     * @param baggagePricing
     *        baggagePricing from zml file
     */
    public void setBaggagePricing(int baggagePricing) {
        this.baggagePricing = baggagePricing;
    }

    /**
     * Gets the size of baggage classes .
     * @return
     *      baggage classes
     */
    public int getBaggageClass() {
        return baggageClass;
    }

    /**
     * Set the size of baggage class, used for reading yml file
     *
     * @param baggageClass
     *        baggageClass from zml file
     */
    public void setBaggageClass(int baggageClass) {
        this.baggageClass = baggageClass;
    }
}

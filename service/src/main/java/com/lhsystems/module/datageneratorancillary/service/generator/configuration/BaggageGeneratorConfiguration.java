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

    public int getBaggageSize() {
        return baggageSize;
    }

    public void setBaggageSize(int baggageSize) {
        this.baggageSize = baggageSize;
    }

    public int getBaggageLimits() {
        return baggageLimits;
    }

    public void setBaggageLimits(int baggageLimits) {
        this.baggageLimits = baggageLimits;
    }

    public int getBaggagePricing() {
        return baggagePricing;
    }

    public void setBaggagePricing(int baggagePricing) {
        this.baggagePricing = baggagePricing;
    }

    public int getBaggageClass() {
        return baggageClass;
    }

    public void setBaggageClass(int baggageClass) {
        this.baggageClass = baggageClass;
    }
}

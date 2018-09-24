package com.lhsystems.module.data.generator.ancillary.generator.configuration;

//@Component
//@ConfigurationProperties(prefix="baggage")
public class BaggageGeneratorConfiguration {

    private int baggageSize;
    private int baggageLimits;
    private int baggagePricing;
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

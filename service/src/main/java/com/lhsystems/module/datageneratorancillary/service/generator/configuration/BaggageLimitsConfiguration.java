package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage limits generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageLimitsConfiguration {

    /** The maximum count. */
    private int maximumCount;

    /** The maximum weight. */
    private double maximumWeight;

    /** The minimum count. */
    private int minimumCount;

    /** The minimum weight. */
    private double minimumWeight;

    /** The number of how many baggage limits should be generated. */
    private int numberBaggageLimits;

    /**
     * Instantiates a new baggage limits configuration.
     */
    public BaggageLimitsConfiguration() {
    }

    /**
     * Gets the maximum count.
     *
     * @return the maximum count
     */
    public int getMaximumCount() {
        return maximumCount;
    }

    /**
     * Gets the maximum weight.
     *
     * @return the maximum weight
     */
    public double getMaximumWeight() {
        return maximumWeight;
    }

    /**
     * Gets the minimum count.
     *
     * @return the minimum count
     */
    public int getMinimumCount() {
        return minimumCount;
    }

    /**
     * Gets the minimum weight.
     *
     * @return the minimum weight
     */
    public double getMinimumWeight() {
        return minimumWeight;
    }

    /**
     * Gets the number of baggage limits.
     *
     * @return the number baggage limits
     */
    public int getNumberBaggageLimits() {
        return numberBaggageLimits;
    }

    /**
     * Sets the maximum count.
     *
     * @param paramMaximumCount
     *            the new maximum count
     */
    public void setMaximumCount(final int paramMaximumCount) {
        maximumCount = paramMaximumCount;
    }

    /**
     * Sets the maximum weight.
     *
     * @param paramMaximumWeight
     *            the new maximum weight
     */
    public void setMaximumWeight(final double paramMaximumWeight) {
        maximumWeight = paramMaximumWeight;
    }

    /**
     * Sets the minimum count.
     *
     * @param paramMinimumCount
     *            the new minimum count
     */
    public void setMinimumCount(final int paramMinimumCount) {
        minimumCount = paramMinimumCount;
    }

    /**
     * Sets the minimum weight.
     *
     * @param paramMinimumWeight
     *            the new minimum weight
     */
    public void setMinimumWeight(final double paramMinimumWeight) {
        minimumWeight = paramMinimumWeight;
    }

    /**
     * Sets the number of baggage limits.
     *
     * @param paramNumberBaggageLimits
     *            the new number baggage limits
     */
    public void setNumberBaggageLimits(final int paramNumberBaggageLimits) {
        numberBaggageLimits = paramNumberBaggageLimits;
    }

}

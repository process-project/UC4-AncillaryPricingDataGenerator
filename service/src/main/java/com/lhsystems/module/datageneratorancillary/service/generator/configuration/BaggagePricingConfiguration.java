package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage pricing generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggagePricingConfiguration {

    /** Upper limit of an additional price. */
    private double maximumAdditionalPrice;

    /** Upper limit of a first price. */
    private double maximumFirstPrice;

    /** Upper limit of a second price. */
    private double maximumSecondPrice;

    /** Lower limit of an additional price. */
    private double minimumAdditionalPrice;

    /** Lower limit of a first price. */
    private double minimumFirstPrice;

    /** Lower limit of a second price. */
    private double minimumSecondPrice;

    /** The number of baggage pricing objects to be generated. */
    private int numberBaggagePricing;

    /**
     * Instantiates a new baggage pricing configuration.
     */
    public BaggagePricingConfiguration() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Gets the maximum additional price.
     *
     * @return the maximum additional price
     */
    public double getMaximumAdditionalPrice() {
        return maximumAdditionalPrice;
    }

    /**
     * Gets the maximum first price.
     *
     * @return the maximum first price
     */
    public double getMaximumFirstPrice() {
        return maximumFirstPrice;
    }

    /**
     * Gets the maximum second price.
     *
     * @return the maximum second price
     */
    public double getMaximumSecondPrice() {
        return maximumSecondPrice;
    }

    /**
     * Gets the minimum additional price.
     *
     * @return the minimum additional price
     */
    public double getMinimumAdditionalPrice() {
        return minimumAdditionalPrice;
    }

    /**
     * Gets the minimum first price.
     *
     * @return the minimum first price
     */
    public double getMinimumFirstPrice() {
        return minimumFirstPrice;
    }

    /**
     * Gets the minimum second price.
     *
     * @return the minimum second price
     */
    public double getMinimumSecondPrice() {
        return minimumSecondPrice;
    }

    /**
     * Gets the number baggage pricing.
     *
     * @return the number baggage pricing
     */
    public int getNumberBaggagePricing() {
        return numberBaggagePricing;
    }

    /**
     * Sets the maximum additional price.
     *
     * @param paramMaximumAdditionalPrice
     *            the new maximum additional price
     */
    public void setMaximumAdditionalPrice(final double paramMaximumAdditionalPrice) {
        this.maximumAdditionalPrice = paramMaximumAdditionalPrice;
    }

    /**
     * Sets the maximum first price.
     *
     * @param paramMaximumFirstPrice
     *            the new maximum first price
     */
    public void setMaximumFirstPrice(final double paramMaximumFirstPrice) {
        this.maximumFirstPrice = paramMaximumFirstPrice;
    }

    /**
     * Sets the maximum second price.
     *
     * @param paramMaximumSecondPrice
     *            the new maximum second price
     */
    public void setMaximumSecondPrice(final double paramMaximumSecondPrice) {
        this.maximumSecondPrice = paramMaximumSecondPrice;
    }

    /**
     * Sets the minimum additional price.
     *
     * @param paramMinimumAdditionalPrice
     *            the new minimum additional price
     */
    public void setMinimumAdditionalPrice(final double paramMinimumAdditionalPrice) {
        this.minimumAdditionalPrice = paramMinimumAdditionalPrice;
    }

    /**
     * Sets the minimum first price.
     *
     * @param paramMinimumFirstPrice
     *            the new minimum first price
     */
    public void setMinimumFirstPrice(final double paramMinimumFirstPrice) {
        this.minimumFirstPrice = paramMinimumFirstPrice;
    }

    /**
     * Sets the minimum second price.
     *
     * @param paramMinimumSecondPrice
     *            the new minimum second price
     */
    public void setMinimumSecondPrice(final double paramMinimumSecondPrice) {
        this.minimumSecondPrice = paramMinimumSecondPrice;
    }

    /**
     * Sets the number baggage pricing.
     *
     * @param paramMumberBaggagePricing
     *            the new number baggage pricing
     */
    public void setNumberBaggagePricing(final int paramMumberBaggagePricing) {
        this.numberBaggagePricing = paramMumberBaggagePricing;
    }

}

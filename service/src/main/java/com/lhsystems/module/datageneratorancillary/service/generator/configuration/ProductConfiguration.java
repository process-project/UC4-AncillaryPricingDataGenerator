package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage class generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ProductConfiguration {

    /** The maximum number of baggage classes. */
    private int maximumNumberBaggageClasses;

    /** The maximum number of seat groups offered in a product. */
    private int maximumNumberSeatGroups;

    /** The minimum number of baggage classes. */
    private int minimumNumberBaggageClasses;

    /** The minimum number of seat groups offered in a product. */
    private int minimumNumberSeatGroups;

    /** The number of products to generate. */
    private int numberProduct;

    /**
     * Instantiates a new product configuration.
     */
    public ProductConfiguration() {
    }

    /**
     * Gets the maximum number of baggage classes.
     *
     * @return the maximum number of baggage classes
     */
    public int getMaximumNumberBaggageClasses() {
        return maximumNumberBaggageClasses;
    }

    /**
     * Gets the maximum number of seat groups.
     *
     * @return the maximum number of seat groups
     */
    public int getMaximumNumberSeatGroups() {
        return maximumNumberSeatGroups;
    }

    /**
     * Gets the minimum number of baggage classes.
     *
     * @return the minimum number of baggage classes
     */
    public int getMinimumNumberBaggageClasses() {
        return minimumNumberBaggageClasses;
    }

    /**
     * Gets the minimum number of seat groups.
     *
     * @return the minimum number of seat groups
     */
    public int getMinimumNumberSeatGroups() {
        return minimumNumberSeatGroups;
    }

    /**
     * Gets the number of products.
     *
     * @return the number of products
     */
    public int getNumberProduct() {
        return numberProduct;
    }

    /**
     * Sets the maximum number of baggage classes.
     *
     * @param paramMaximumNumberBaggageClasses
     *            the new maximum number of baggage classes
     */
    public void setMaximumNumberBaggageClasses(
            final int paramMaximumNumberBaggageClasses) {
        maximumNumberBaggageClasses = paramMaximumNumberBaggageClasses;
    }

    /**
     * Sets the maximum number of seat groups.
     *
     * @param paramMaximumNumberSeatGroups
     *            the new maximum number seat groups
     */
    public void setMaximumNumberSeatGroups(
            final int paramMaximumNumberSeatGroups) {
        maximumNumberSeatGroups = paramMaximumNumberSeatGroups;
    }

    /**
     * Sets the minimum number of baggage classes.
     *
     * @param paramMinimumNumberBaggageClasses
     *            the new minimum number of baggage classes
     */
    public void setMinimumNumberBaggageClasses(
            final int paramMinimumNumberBaggageClasses) {
        minimumNumberBaggageClasses = paramMinimumNumberBaggageClasses;
    }

    /**
     * Sets the minimum number of seat groups.
     *
     * @param paramMinimumNumberSeatGroups
     *            the new minimum number seat groups
     */
    public void setMinimumNumberSeatGroups(final int paramMinimumNumberSeatGroups) {
        this.minimumNumberSeatGroups = paramMinimumNumberSeatGroups;
    }

    /**
     * Sets the number of products.
     *
     * @param paramNumberProduct
     *            the new number product
     */
    public void setNumberProduct(final int paramNumberProduct) {
        numberProduct = paramNumberProduct;
    }


}

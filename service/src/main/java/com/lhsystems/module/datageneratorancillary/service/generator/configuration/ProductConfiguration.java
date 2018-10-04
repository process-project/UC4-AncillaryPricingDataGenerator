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

    /** The minimum number of baggage classes. */
    private int minimumNumberBaggageClasses;

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
     * Gets the minimum number of baggage classes.
     *
     * @return the minimum number of baggage classes
     */
    public int getMinimumNumberBaggageClasses() {
        return minimumNumberBaggageClasses;
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
     * Sets the number product.
     *
     * @param paramNumberProduct
     *            the new number product
     */
    public void setNumberProduct(final int paramNumberProduct) {
        numberProduct = paramNumberProduct;
    }

}

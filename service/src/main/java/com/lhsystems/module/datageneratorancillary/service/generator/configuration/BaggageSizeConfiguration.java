package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage size generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageSizeConfiguration {

    /**
     * The maximum upper limit of the circumference of a baggage limits object.
     */
    private double maximumCircumference;

    /**
     * The maximum upper limit of the length for any side of a piece of baggage.
     */
    private double maximumSideLength;

    /**
     * The minimum upper limit of the circumference of a baggage limits object.
     */
    private double minimumCircumference;

    /**
     * The minimum upper limit of the length for any side of a piece of baggage.
     */
    private double minimumSideLength;

    /** The number of baggage size objects to be generated. */
    private int numberBaggageSize;

    /**
     * Instantiates a new baggage size configuration.
     */
    public BaggageSizeConfiguration() {
    }

    /**
     * Gets the maximum circumference.
     *
     * @return the maximum circumference
     */
    public double getMaximumCircumference() {
        return maximumCircumference;
    }

    /**
     * Gets the maximum side length.
     *
     * @return the maximum side length
     */
    public double getMaximumSideLength() {
        return maximumSideLength;
    }

    /**
     * Gets the minimum circumference.
     *
     * @return the minimum circumference
     */
    public double getMinimumCircumference() {
        return minimumCircumference;
    }

    /**
     * Gets the minimum side length.
     *
     * @return the minimum side length
     */
    public double getMinimumSideLength() {
        return minimumSideLength;
    }

    /**
     * Gets the number of baggage size objects to be generated.
     *
     * @return the number baggage size of baggage size objects
     */
    public int getNumberBaggageSize() {
        return numberBaggageSize;
    }

    /**
     * Sets the maximum circumference.
     *
     * @param paramMaximumCircumference
     *            the new maximum circumference
     */
    public void setMaximumCircumference(final double paramMaximumCircumference) {
        maximumCircumference = paramMaximumCircumference;
    }

    /**
     * Sets the maximum side length.
     *
     * @param paramMaximumSideLength
     *            the new maximum side length
     */
    public void setMaximumSideLength(final double paramMaximumSideLength) {
        maximumSideLength = paramMaximumSideLength;
    }

    /**
     * Sets the minimum circumference.
     *
     * @param paramMinimumCircumference
     *            the new minimum circumference
     */
    public void setMinimumCircumference(final double paramMinimumCircumference) {
        minimumCircumference = paramMinimumCircumference;
    }

    /**
     * Sets the minimum side length.
     *
     * @param paramMinimumSideLength
     *            the new minimum side length
     */
    public void setMinimumSideLength(final double paramMinimumSideLength) {
        minimumSideLength = paramMinimumSideLength;
    }

    /**
     * Sets the number of baggage size objects to be generated.
     *
     * @param paramNumberBaggageSize
     *            the new number of baggage size objects
     */
    public void setNumberBaggageSize(final int paramNumberBaggageSize) {
        numberBaggageSize = paramNumberBaggageSize;
    }
}

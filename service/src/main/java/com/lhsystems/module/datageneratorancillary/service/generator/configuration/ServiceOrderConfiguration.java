package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for service order generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ServiceOrderConfiguration {

    /** The maximum number bags. */
    private int maximumNumberBags;

    /** The minimum number bags. */
    private int minimumNumberBags;

    /**
     * The probability of any additional service to be included in a service
     * order.
     */
    private double serviceProbability;

    /**
     * Instantiates a new service order configuration.
     */
    public ServiceOrderConfiguration() {
    }

    /**
     * Gets the maximum number bags.
     *
     * @return the maximum number bags
     */
    public int getMaximumNumberBags() {
        return maximumNumberBags;
    }

    /**
     * Gets the minimum number bags.
     *
     * @return the minimum number bags
     */
    public int getMinimumNumberBags() {
        return minimumNumberBags;
    }

    /**
     * Gets the service probability.
     *
     * @return the service probability
     */
    public double getServiceProbability() {
        return serviceProbability;
    }

    /**
     * Sets the maximum number bags.
     *
     * @param paramMaximumNumberBags
     *            the new maximum number bags
     */
    public void setMaximumNumberBags(final int paramMaximumNumberBags) {
        maximumNumberBags = paramMaximumNumberBags;
    }

    /**
     * Sets the minimum number bags.
     *
     * @param paramMinimumNumberBags
     *            the new minimum number bags
     */
    public void setMinimumNumberBags(final int paramMinimumNumberBags) {
        minimumNumberBags = paramMinimumNumberBags;
    }

    /**
     * Sets the service probability.
     *
     * @param paramServiceProbability
     *            the new service probability
     */
    public void setServiceProbability(final double paramServiceProbability) {
        this.serviceProbability = paramServiceProbability;
    }

}

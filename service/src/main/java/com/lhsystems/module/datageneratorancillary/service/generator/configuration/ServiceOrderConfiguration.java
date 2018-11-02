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
     * Sets the maximum number bags.
     *
     * @param paramMaximumNumberBags
     *            the new maximum number bags
     */
    public void setMaximumNumberBags(final int paramMaximumNumberBags) {
        maximumNumberBags = paramMaximumNumberBags;
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
     * Sets the minimum number bags.
     *
     * @param paramMinimumNumberBags
     *            the new minimum number bags
     */
    public void setMinimumNumberBags(final int paramMinimumNumberBags) {
        minimumNumberBags = paramMinimumNumberBags;
    }

}

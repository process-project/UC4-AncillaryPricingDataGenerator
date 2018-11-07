package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for seating model generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatingModelConfiguration {

    /** The maximum number of seat groups. */
    private int maximumNumberSeatGroups;

    /** The minimum number of seat groups. */
    private int minimumNumberSeatGroups;

    /** The number of seating model. */
    private int numberSeatingModel;

    /**
     * Instantiates a new seating model configuration.
     */
    public SeatingModelConfiguration() {
    }

    /**
     * Gets the maximum number of seat groups.
     *
     * @return the maximum number seat groups
     */
    public int getMaximumNumberSeatGroups() {
        return maximumNumberSeatGroups;
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
     * Gets the number of seating models.
     *
     * @return the number of seating models
     */
    public int getNumberSeatingModel() {
        return numberSeatingModel;
    }

    /**
     * Sets the maximum number of seat groups.
     *
     * @param paramMaximumNumberSeatGroups
     *            the new maximum number of seat groups
     */
    public void setMaximumNumberSeatGroups(final int paramMaximumNumberSeatGroups) {
        this.maximumNumberSeatGroups = paramMaximumNumberSeatGroups;
    }

    /**
     * Sets the minimum number of seat groups.
     *
     * @param paramMinimumNumberSeatGroups
     *            the new minimum number of seat groups
     */
    public void setMinimumNumberSeatGroups(final int paramMinimumNumberSeatGroups) {
        this.minimumNumberSeatGroups = paramMinimumNumberSeatGroups;
    }

    /**
     * Sets the number of seating models.
     *
     * @param paramNumberSeatingModel
     *            the new number of seating models
     */
    public void setNumberSeatingModel(final int paramNumberSeatingModel) {
        this.numberSeatingModel = paramNumberSeatingModel;
    }

}

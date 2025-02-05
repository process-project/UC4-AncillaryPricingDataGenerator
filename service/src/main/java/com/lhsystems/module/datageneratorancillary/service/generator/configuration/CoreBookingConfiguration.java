package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for core booking generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class CoreBookingConfiguration {

    /** The maximum number of passengers in one booking. */
    private int maximumNumberPassengers;

    /** The minimum number of passengers. */
    private int minimumNumberPassengers;

    /** The range of days before departure. */
    private int rangeOfDaysBeforeDeparture;

    /**
     * Instantiates a new core booking generator configuration object.
     */
    public CoreBookingConfiguration() {
    }

    /**
     * Gets the maximum number of passengers.
     *
     * @return the maximum number of passengers
     */
    public int getMaximumNumberPassengers() {
        return maximumNumberPassengers;
    }

    /**
     * Gets the minimum number of passengers.
     *
     * @return the minimum number of passengers
     */
    public int getMinimumNumberPassengers() {
        return minimumNumberPassengers;
    }

    /**
     * Gets the range of days before departure.
     *
     * @return the range of days before departure
     */
    public int getRangeOfDaysBeforeDeparture() {
        return rangeOfDaysBeforeDeparture;
    }

    /**
     * Sets the maximum number of passengers.
     *
     * @param paramMaximumNumberPassengers
     *            the new maximum number of passengers
     */
    public void setMaximumNumberPassengers(
            final int paramMaximumNumberPassengers) {
        maximumNumberPassengers = paramMaximumNumberPassengers;
    }

    /**
     * Sets the minimum number of passengers.
     *
     * @param paramMinimumNumberPassengers
     *            the new minimum number of passengers
     */
    public void setMinimumNumberPassengers(
            final int paramMinimumNumberPassengers) {
        minimumNumberPassengers = paramMinimumNumberPassengers;
    }

    /**
     * Sets the range of days before departure.
     *
     * @param paramRangeOfDaysBeforeDeparture
     *            the new range of days before departure
     */
    public void setRangeOfDaysBeforeDeparture(
            final int paramRangeOfDaysBeforeDeparture) {
        rangeOfDaysBeforeDeparture = paramRangeOfDaysBeforeDeparture;
    }

}

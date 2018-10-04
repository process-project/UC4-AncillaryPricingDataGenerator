package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for booking generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BookingConfiguration {

    /** The maximum number of bags. */
    private int maximumNumberBags;

    /** The maximum number of passengers. */
    private int maximumNumberPassengers;

    /** The minimum number of bags. */
    private int minimumNumberBags;

    /** The minimum number of passengers. */
    private int minimumNumberPassengers;

    /** The number of bookings to be generated. */
    private int numberBookings;

    /**
     * Instantiates a new booking configuration.
     */
    public BookingConfiguration() {
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
     * Gets the maximum number passengers.
     *
     * @return the maximum number passengers
     */
    public int getMaximumNumberPassengers() {
        return maximumNumberPassengers;
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
     * Gets the minimum number passengers.
     *
     * @return the minimum number passengers
     */
    public int getMinimumNumberPassengers() {
        return minimumNumberPassengers;
    }

    /**
     * Gets the number bookings.
     *
     * @return the number bookings
     */
    public int getNumberBookings() {
        return numberBookings;
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
     * Sets the maximum number passengers.
     *
     * @param paramMaximumNumberPassengers
     *            the new maximum number passengers
     */
    public void setMaximumNumberPassengers(final int paramMaximumNumberPassengers) {
        maximumNumberPassengers = paramMaximumNumberPassengers;
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
     * Sets the minimum number passengers.
     *
     * @param paramMinimumNumberPassengers
     *            the new minimum number passengers
     */
    public void setMinimumNumberPassengers(final int paramMinimumNumberPassengers) {
        minimumNumberPassengers = paramMinimumNumberPassengers;
    }

    /**
     * Sets the number bookings.
     *
     * @param paramNumberBookings
     *            the new number bookings
     */
    public void setNumberBookings(final int paramNumberBookings) {
        numberBookings = paramNumberBookings;
    }

}

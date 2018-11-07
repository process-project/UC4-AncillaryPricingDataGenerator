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

    /** The maximum number of Days before Departure. */
    private int rangeOfDaysBeforeDeparture;

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
     * Gets the maximum number of passengers.
     *
     * @return the maximum number of passengers
     */
    public int getMaximumNumberPassengers() {
        return maximumNumberPassengers;
    }

    /**
     * Gets the minimum number of bags.
     *
     * @return the minimum number of bags
     */
    public int getMinimumNumberBags() {
        return minimumNumberBags;
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
     * Gets the number of bookings to be generated.
     *
     * @return the number of bookings to be generated
     */
    public int getNumberBookings() {
        return numberBookings;
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
     * Sets the maximum number of bags.
     *
     * @param paramMaximumNumberBags
     *            the new maximum number of bags
     */
    public void setMaximumNumberBags(final int paramMaximumNumberBags) {
        maximumNumberBags = paramMaximumNumberBags;
    }

    /**
     * Sets the maximum number of passengers.
     *
     * @param paramMaximumNumberPassengers
     *            the new maximum number passengers
     */
    public void setMaximumNumberPassengers(final int paramMaximumNumberPassengers) {
        maximumNumberPassengers = paramMaximumNumberPassengers;
    }

    /**
     * Sets the minimum number of bags.
     *
     * @param paramMinimumNumberBags
     *            the new minimum number of bags
     */
    public void setMinimumNumberBags(final int paramMinimumNumberBags) {
        minimumNumberBags = paramMinimumNumberBags;
    }

    /**
     * Sets the minimum number of passengers.
     *
     * @param paramMinimumNumberPassengers
     *            the new minimum number of passengers
     */
    public void setMinimumNumberPassengers(final int paramMinimumNumberPassengers) {
        minimumNumberPassengers = paramMinimumNumberPassengers;
    }

    /**
     * Sets the number of bookings.
     *
     * @param paramNumberBookings
     *            the new number of bookings
     */
    public void setNumberBookings(final int paramNumberBookings) {
        numberBookings = paramNumberBookings;
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

package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for seat group generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatGroupConfiguration {

    /** The maximum number of seats in one seat group. */
    private int maximumNumberSeats;

    /** The maximum seat price. */
    private int maximumSeatPrice;

    /** The minimum number of seats in one seat group. */
    private int minimumNumberSeats;

    /** The minimum seat price. */
    private int minimumSeatPrice;

    /** The number seat group. */
    private int numberSeatGroup;

    /**
     * Instantiates a new seat group configuration.
     */
    public SeatGroupConfiguration() {
    }

    /**
     * Gets the maximum number of seats.
     *
     * @return the maximum number of seats
     */
    public int getMaximumNumberSeats() {
        return maximumNumberSeats;
    }

    /**
     * Gets the maximum seat price.
     *
     * @return the maximum seat price
     */
    public int getMaximumSeatPrice() {
        return maximumSeatPrice;
    }

    /**
     * Gets the minimum number of seats.
     *
     * @return the minimum number of seats
     */
    public int getMinimumNumberSeats() {
        return minimumNumberSeats;
    }

    /**
     * Gets the minimum seat price.
     *
     * @return the minimum seat price
     */
    public int getMinimumSeatPrice() {
        return minimumSeatPrice;
    }

    /**
     * Gets the number of seat groups.
     *
     * @return the number seat group
     */
    public int getNumberSeatGroup() {
        return numberSeatGroup;
    }

    /**
     * Sets the maximum number of seats.
     *
     * @param paramMaximumNumberSeats
     *            the new maximum number of seats
     */
    public void setMaximumNumberSeats(final int paramMaximumNumberSeats) {
        this.maximumNumberSeats = paramMaximumNumberSeats;
    }

    /**
     * Sets the maximum seat price.
     *
     * @param paramMaximumSeatPrice
     *            the new maximum seat price
     */
    public void setMaximumSeatPrice(final int paramMaximumSeatPrice) {
        this.maximumSeatPrice = paramMaximumSeatPrice;
    }

    /**
     * Sets the minimum number of seats.
     *
     * @param paramMinimumNumberSeats
     *            the new minimum number of seats
     */
    public void setMinimumNumberSeats(final int paramMinimumNumberSeats) {
        this.minimumNumberSeats = paramMinimumNumberSeats;
    }

    /**
     * Sets the minimum seat price.
     *
     * @param paramMinimumSeatPrice
     *            the new minimum seat price
     */
    public void setMinimumSeatPrice(final int paramMinimumSeatPrice) {
        this.minimumSeatPrice = paramMinimumSeatPrice;
    }

    /**
     * Sets the number of seat groups.
     *
     * @param paramNumberSeatGroup
     *            the new number seat group
     */
    public void setNumberSeatGroup(final int paramNumberSeatGroup) {
        this.numberSeatGroup = paramNumberSeatGroup;
    }

}
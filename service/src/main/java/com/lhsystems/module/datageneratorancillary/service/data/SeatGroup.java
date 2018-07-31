package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * a group of seats that all cost the same.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatGroup {

    /** The id of the seat group. */
    private final long id;

    /** The name of the seat group. */
    private final String name;

    /** The number of seats in this group. */
    private final int numberSeats;

    /** The price of each seat. */
    private final double seatPrice;

    /**
     * Instantiates a new seat group.
     *
     * @param paramId
     *            the id
     * @param paramName
     *            the name
     * @param paramNumberSeats
     *            the number of seats
     * @param paramSeatPrice
     *            the seat price
     */
    public SeatGroup(final long paramId, final String paramName,
            final int paramNumberSeats, final double paramSeatPrice) {
        id = paramId;
        name = paramName;
        numberSeats = paramNumberSeats;
        seatPrice = paramSeatPrice;
    }

    /**
     * returns the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the number seats.
     *
     * @return the number seats
     */
    public int getNumberSeats() {
        return numberSeats;
    }

    /**
     * returns the seat price.
     *
     * @return the seat price
     */
    public double getSeatPrice() {
        return seatPrice;
    }

}

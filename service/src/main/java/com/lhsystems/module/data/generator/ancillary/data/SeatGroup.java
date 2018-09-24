package com.lhsystems.module.data.generator.ancillary.data;

import javax.persistence.*;

/**
 * a group of seats that all cost the same.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "SeatGroup")
public final class SeatGroup {

    /** The id of the seat group. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    /** The name of the seat group. */
    @Column(name = "NAME")
    private final String name;

    /** The number of seats in this group. */
    @Column(name = "NUMBER_SEATS")
    private final int numberSeats;

    /** The price of each seat. */
    @Column(name = "SEAT_PRICE")
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
     * Default Constructor needed for an Entity. Instantiates a new seat group
     * class.
     */
    public SeatGroup() {
        name = "default";
        id = 0L;
        numberSeats = 0;
        seatPrice = 0;
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

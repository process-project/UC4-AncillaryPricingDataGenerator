package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * a group of seats that all cost the same.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "SeatGroup")
public final class SeatGroup extends Service {

    /** The number of seats in this group. */
    @Column(name = "NUMBER_SEATS")
    private final int numberSeats;

    /** The price of each seat. */
    @Column(name = "SEAT_PRICE")
    private final double seatPrice;

    /**
     * Default Constructor needed for an Entity. Instantiates a new seat group
     * class.
     */
    public SeatGroup() {
        super();
        numberSeats = 0;
        seatPrice = 0;
    }

    /**
     * Instantiates a new seat group.
     *
     * @param paramName
     *            the name
     * @param paramNumberSeats
     *            the number of seats
     * @param paramSeatPrice
     *            the seat price
     */
    public SeatGroup(final String paramName,
            final int paramNumberSeats, final double paramSeatPrice) {
        super(paramName, paramNumberSeats);
        numberSeats = paramNumberSeats;
        seatPrice = paramSeatPrice;
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

    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        return seatPrice * number;
    }

}

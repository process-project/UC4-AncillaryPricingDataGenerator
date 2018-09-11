package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;

/**
 * Data structure representing the seating of a tariff.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class SeatingModel {

    /** The id of a seating model. */
    private final long id;

    /** The seat groups in this model. */
    private final List<SeatGroup> seatGroups;

    /**
     * Instantiates a new seating model.
     *
     * @param paramId
     *            the id
     * @param paramSeatGroups
     *            the seat groups offered in this model
     */
    public SeatingModel(final long paramId,
            final List<SeatGroup> paramSeatGroups) {
        id = paramId;
        seatGroups = paramSeatGroups;
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
     * returns the seat groups.
     *
     * @return the seat groups
     */
    public List<SeatGroup> getSeatGroups() {
        return seatGroups;
    }

}

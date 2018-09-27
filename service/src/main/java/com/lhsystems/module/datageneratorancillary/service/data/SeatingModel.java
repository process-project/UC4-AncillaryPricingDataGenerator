package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.*;
import java.util.List;

/**
 * Data structure representing the seating of a tariff.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "SeatingModel")
public final class SeatingModel {

    /** The id of a seating model. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The seat groups in this model. */
    @OneToMany
    @JoinColumn(name = "SEAT_GROUP")
    private List<SeatGroup> seatGroups;

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
     * Default Constructor needed for an Entity. Instantiates a new seating model
     * class.
     */
    public SeatingModel() {
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

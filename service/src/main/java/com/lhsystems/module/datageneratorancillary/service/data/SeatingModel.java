package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
     * Default Constructor needed for an Entity. Instantiates a new seating model
     * class.
     */
    public SeatingModel() {
    }

    /**
     * Instantiates a new seating model.
     *
     * @param paramSeatGroups
     *            the seat groups offered in this model
     */
    public SeatingModel(final List<SeatGroup> paramSeatGroups) {
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

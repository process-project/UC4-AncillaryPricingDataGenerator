package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

/**
 * Data structure describing the selection of seats in a booking process.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "SeatSelection")
public final class SeatSelection {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The chosen seats. States for each <code>SeatGroup</code>, how many seats
     * are reserved. The overall number of chosen seats equals the number of
     * Passengers
     */
    @ElementCollection
    @MapKeyJoinColumn(name = "SEAT_GROUP_ID", referencedColumnName = "id")
    @CollectionTable(name = "NumberOfChosenSeats")
    private final Map<SeatGroup, Integer> chosenSeats;

    /**
     * The number of days between choosing the seats and the day of departure.
     * Assumes that this customer decision is final.
     */
    @Column(name = "DAYS_BEFORE_DEPARTURE")
    private final int daysBeforeDeparture;

    /**
     * Instantiates a new seat selection. Default Constructer needed for an Entity
     */
    public SeatSelection(){
        chosenSeats = null;
        daysBeforeDeparture = 0;
    }

    /**
     * Instantiates a new seat selection.
     *
     * @param paramChosenSeats
     *            the chosen seats
     * @param paramDaysBeforeDeparture
     *            the days before departure
     */
    public SeatSelection(final Map<SeatGroup, Integer> paramChosenSeats,
            final int paramDaysBeforeDeparture) {
        chosenSeats = paramChosenSeats;
        daysBeforeDeparture = paramDaysBeforeDeparture;
    }
}

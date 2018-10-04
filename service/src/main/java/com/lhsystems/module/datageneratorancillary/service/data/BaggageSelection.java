/*
 *
 */
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
 * Data structure describing the selection of baggage.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "BaggageSelection")
public final class BaggageSelection {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The number of days between choosing the number of bags and the day of
     * departure. Assumes that this customer decision is final.
     */
    @Column(name = "DAYS_BEFORE_DEPARTURE")
    private final int baggageDaysBeforeDeparture;

    /**
     * The chosen amount of baggage. States for each <code>BaggageClass</code>,
     * how many bags are chosen.
     */
    @ElementCollection
    @MapKeyJoinColumn(name = "BAGGAGE_CLASS_ID", referencedColumnName = "id")
    @CollectionTable(name = "ChosenBaggage")
    private final Map<BaggageClass, Integer> chosenBaggage;

    /**
     * Instantiates a new baggage selection. Default constructor needed for
     * entity.
     */
    public BaggageSelection(){
        baggageDaysBeforeDeparture = 0;
        chosenBaggage = null;
    }

    /**
     * Instantiates a new baggage selection object.
     *
     *
     * @param paramBaggageDaysBeforeDeparture
     *            the baggage days before departure
     * @param paramChosenBaggage
     *            the chosen baggage
     */
    public BaggageSelection(
            final Map<BaggageClass, Integer> paramChosenBaggage,
            final int paramBaggageDaysBeforeDeparture) {
        baggageDaysBeforeDeparture = paramBaggageDaysBeforeDeparture;
        chosenBaggage = paramChosenBaggage;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the days before departure the baggage was booked.
     *
     * @return the days before departure
     */
    public int getBaggageDaysBeforeDeparture() {
        return baggageDaysBeforeDeparture;
    }

    /**
     * Gets the chosen baggage.
     *
     * @return the chosen baggage
     */
    public Map<BaggageClass, Integer> getChosenBaggage() {
        return chosenBaggage;
    }
}

package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Defines the limits of a baggage class.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "BaggageLimits")
public final class BaggageLimits {

    /** The maximum size of baggage items. */
    @OneToOne
    @JoinColumn(name = "BAGGAGE_SIZE")
    private final BaggageSize baggageSize;

    /** The maximum count of baggage items. */
    @Column(name = "COUNT_MAX")
    private final int countMax;

    /** The id of the baggage Limits. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    /** The maximum weight of one piece of baggage. */
    @Column(name = "WEIGHT_MAX")
    private final double weightMax;

    /**
     * Default Constructor needed for an Entity. Instantiates a new baggage
     * class.
     */
    public BaggageLimits() {
        baggageSize = null;
        weightMax = 0;
        countMax = 0;
    }

    /**
     * Instantiates new baggage limits .
     *
     * @param paramBaggageSize
     *            the size limits
     * @param paramCountMax
     *            the maximum count
     * @param paramWeightMax
     *            the maximum weight
     */
    public BaggageLimits(final BaggageSize paramBaggageSize,
            final int paramCountMax, final double paramWeightMax) {
        baggageSize = paramBaggageSize;
        countMax = paramCountMax;
        weightMax = paramWeightMax;
    }

    /**
     * Gets the baggage size.
     *
     * @return the baggage size
     */
    public BaggageSize getBaggageSize() {
        return baggageSize;
    }

    /**
     * Gets the maximum count of baggage.
     *
     * @return the maximum count of baggage
     */
    public int getCountMax() {
        return countMax;
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
     * Gets the maximum weight.
     *
     * @return the weight max
     */
    public double getWeightMax() {
        return weightMax;
    }
}

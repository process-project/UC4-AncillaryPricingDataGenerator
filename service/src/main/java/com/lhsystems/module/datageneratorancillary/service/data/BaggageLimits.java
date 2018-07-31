package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * Defines the limits of a baggage class.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageLimits {

    /** The maximum size of baggage items. */
    private final BaggageSize baggageSize;

    /** The maximum count of baggage items. */
    private final int countMax;

    /** The id of the baggage Limits. */
    private final long id;


    /** The maximum weight of one piece of baggage. */
    private final double weightMax;


    /**
     * Instantiates new baggage limits .
     *
     * @param paramId
     *            the id
     * @param paramBaggageSize
     *            the size limits
     * @param paramCountMax
     *            the maximum count
     * @param paramWeightMax
     *            the maximum weight
     */
    public BaggageLimits(final long paramId, final BaggageSize paramBaggageSize,
            final int paramCountMax, final double paramWeightMax) {
        id = paramId;
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

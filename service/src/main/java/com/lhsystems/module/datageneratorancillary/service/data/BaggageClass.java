package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * data structure representing a baggage class in an ancillary model.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageClass {

    /** The baggage limits. */
    private final BaggageLimits baggageLimits;

    /** The baggage pricing. */
    private final BaggagePricing baggagePricing;

    /** The id of the baggage class. */
    private final long id;

    /** The name of this baggage class. */
    private final String name;

    /**
     * Instantiates a new baggage class.
     *
     * @param paramId
     *            the id
     * @param paramName
     *            the name
     * @param paramBaggageLimits
     *            the baggage limits
     * @param paramBaggagePricing
     *            the baggage pricing
     */
    public BaggageClass(final long paramId, final String paramName,
            final BaggageLimits paramBaggageLimits,
            final BaggagePricing paramBaggagePricing) {
        id = paramId;
        name = paramName;
        baggageLimits = paramBaggageLimits;
        baggagePricing = paramBaggagePricing;
    }

    /**
     * Gets the baggage limits.
     *
     * @return the baggage limits
     */
    public BaggageLimits getBaggageLimits() {
        return baggageLimits;
    }

    /**
     * returns the baggage pricing.
     *
     * @return the baggage pricing
     */
    public BaggagePricing getBaggagePricing() {
        return baggagePricing;
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
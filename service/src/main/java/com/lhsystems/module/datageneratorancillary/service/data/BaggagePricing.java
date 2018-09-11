package com.lhsystems.module.datageneratorancillary.service.data;

/**
 * Defines the prices in a BaggageClasss.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggagePricing {

    /** The price of each additional bag. */
    private final double priceAdditionalBag;

    /** The price of the first bag. */
    private final double priceFirstBag;

    /** The id of the pricing model. */
    private final long id;

    /** The price of the second bag. */
    private final double priceSecondBag;

    /**
     * Instantiates a new baggage pricing.
     *
     * @param paramId
     *            the id
     * @param firstPrice
     *            the first bag price
     * @param secondPrice
     *            the second bag price
     * @param additionalPrice
     *            the additional bag price
     */
    public BaggagePricing(final long paramId, final double firstPrice,
            final double secondPrice, final double additionalPrice) {
        id = paramId;
        priceFirstBag = firstPrice;
        priceSecondBag = secondPrice;
        priceAdditionalBag = additionalPrice;
    }

    /**
     * Gets the additional bag price.
     *
     * @return the additional price
     */
    public double getAdditionalPrice() {
        return priceAdditionalBag;
    }

    /**
     * Gets the first bag price.
     *
     * @return the first price
     */
    public double getFirstPrice() {
        return priceFirstBag;
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
     * Gets the second bag price.
     *
     * @return the second price
     */
    public double getSecondPrice() {
        return priceSecondBag;
    }
}
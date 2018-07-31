package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.Market;

/**
 * data structure representing the tariffs offered by an airline.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class Tariff {

    /** The product a tariff belongs to. */
    private final Product product;

    /** The id of a tariff. */
    private final long id;

    /** The market the tariff is offered in. */
    private final Market market;

    /** The price of a tariff. */
    private final double price;

    /** The seating model in this tariff. */
    private final SeatingModel seating;

    /**
     * Instantiates a new tariff.
     *
     * @param paramId
     *            the id
     * @param paramPrice
     *            the price
     * @param paramSeating
     *            the seating
     * @param paramProduct
     *            the product
     * @param paramMarket
     *            the available markets
     */
    public Tariff(final long paramId, final double paramPrice, final SeatingModel paramSeating,
            final Product paramProduct, final Market paramMarket) {
        id = paramId;
        price = paramPrice;
        seating = paramSeating;
        product = paramProduct;
        market = paramMarket;
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
     * returns the market.
     *
     * @return the market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * returns the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * returns the product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * returns the seating.
     *
     * @return the seating
     */
    public SeatingModel getSeating() {
        return seating;
    }

}

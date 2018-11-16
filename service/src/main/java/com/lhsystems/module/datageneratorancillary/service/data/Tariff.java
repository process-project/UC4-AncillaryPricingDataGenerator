package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * data structure representing the tariffs offered by an airline.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Tariff")
public final class Tariff {

    /**
     * The product a tariff belongs to.
     */
    @OneToOne
    @JoinColumn(name = "PRODUCT")
    private final Product product;

    /**
     * The flights that this tariff belongs to.
     */
    @ManyToMany(mappedBy = "bookableTariffs")
    private List<Flight> flights;

    /** The id of a tariff. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The market the tariff is offered in.
     */
    @Enumerated(EnumType.STRING)
    private final Market market;

    /**
     * The price of a tariff.
     */
    @Column(name = "PRICE")
    private final double price;

    /**
     * Default Constructor needed for an Entity. Instantiates a new tariff
     * class.
     */
    public Tariff() {
        price = 0;
        product = null;
        market = null;
    }

    /**
     * Instantiates a new tariff.
     *
     * @param paramPrice   the price
     * @param paramProduct the product
     * @param paramMarket  the available markets
     */
    public Tariff(final double paramPrice,
            final Product paramProduct, final Market paramMarket) {
        price = paramPrice;
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
     * Gets flights.
     *
     * @return the flights
     */
    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Sets flights.
     *
     * @param flightsParam
     *            the flights
     */
    public void setFlights(final List<Flight> flightsParam) {
        flights = flightsParam;
    }

}

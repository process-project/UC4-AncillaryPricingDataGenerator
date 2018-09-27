package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.*;
import java.util.List;

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
    private final long id;

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
     * The seating model in this tariff.
     */
    @OneToOne
    @JoinColumn(name = "SEATING_MODEL")
    private final SeatingModel seating;

    /**
     * Instantiates a new tariff.
     *
     * @param paramId      the id
     * @param paramPrice   the price
     * @param paramSeating the seating
     * @param paramProduct the product
     * @param paramMarket  the available markets
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
     * Default Constructor needed for an Entity. Instantiates a new tariff
     * class.
     */
    public Tariff() {
        id = 0;
        price = 0;
        seating = null;
        product = null;
        market = null;
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
     * @param flightsParam the flights
     */
    public void setFlights(final List<Flight> flightsParam) {
        this.flights = flightsParam;
    }

}

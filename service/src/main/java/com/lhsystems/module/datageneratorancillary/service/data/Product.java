package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * data structure representing a product in a compartment.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Product")
public final class Product {

    /** The baggage classes that are offered in this product. */
    @OneToMany
    @JoinColumn(name = "BAGGAGE_CLASS")
    private List<BaggageClass> baggageClasses;

    /** The compartment this product belongs to. */
    @OneToOne
    @JoinColumn(name = "COMPARTMENT")
    private final Compartment compartment;

    /** The id of this product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The number of included bags in each baggage class. */
    @ElementCollection
    @MapKeyJoinColumn(name = "BAGGAGE_CLASS_ID", referencedColumnName = "id")
    @CollectionTable(name = "NumberOfIncludedBags")
    private final Map<BaggageClass, Integer> numberOfIncludedBagsByBaggageClass;

    /** The name of the product. */
    @Column(name = "NAME")
    private final String name;

    /**
     * Default Constructor needed for an Entity. Instantiates a new product
     * class.
     */
    public Product() {
        name = null;
        compartment = null;
        numberOfIncludedBagsByBaggageClass = null;
    }

    /**
     * Instantiates a new product.
     *
     * @param paramName
     *            the name
     * @param paramCompartment
     *            the compartment
     * @param paramBaggageClasses
     *            the baggage classes
     * @param paramNumberOfIncludedBags
     *            the number of included bags
     */
    public Product(final String paramName,
            final Compartment paramCompartment,
            final List<BaggageClass> paramBaggageClasses,
            final Map<BaggageClass, Integer> paramNumberOfIncludedBags) {
        name = paramName;
        compartment = paramCompartment;
        baggageClasses = paramBaggageClasses;
        numberOfIncludedBagsByBaggageClass = paramNumberOfIncludedBags;
    }

    /**
     * returns the baggage classes.
     *
     * @return the baggage classes
     */
    public List<BaggageClass> getBaggageClasses() {
        return baggageClasses;
    }

    /**
     * returns the compartment.
     *
     * @return the compartment
     */
    public Compartment getCompartment() {
        return compartment;
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
     * returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the number of included bags.
     *
     * @return the number of included bags
     */
    public Map<BaggageClass, Integer> getNumberOfIncludedBagsByBaggageClass() {
        return numberOfIncludedBagsByBaggageClass;
    }

}

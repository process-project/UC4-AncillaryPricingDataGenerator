package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.List;
import java.util.Map;

/**
 * data structure representing a product in a compartment.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class Product {

    /** The baggage classes that are offered in this product. */
    private final List<BaggageClass> baggageClasses;

    /** The compartment this product belongs to. */
    private final Compartment compartment;

    /** The id of this product. */
    private final long id;

    /** The number of included bags in each baggage class. */
    private final Map<BaggageClass, Integer> numberOfIncludedBags;

    /** The name of the product. */
    private final String name;

    /**
     * Instantiates a new product.
     *
     * @param paramId
     *            the id
     * @param paramName
     *            the name
     * @param paramCompartment
     *            the compartment
     * @param paramBaggageClasses
     *            the baggage classes
     * @param paramNumberOfIncludedBags
     *            the number of included bags
     */
    public Product(final long paramId, final String paramName,
            final Compartment paramCompartment,
            final List<BaggageClass> paramBaggageClasses,
            final Map<BaggageClass, Integer> paramNumberOfIncludedBags) {
        id = paramId;
        name = paramName;
        compartment = paramCompartment;
        baggageClasses = paramBaggageClasses;
        numberOfIncludedBags = paramNumberOfIncludedBags;
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
     * returns the number of included bags.
     *
     * @return the number of included bags
     */
    public Map<BaggageClass, Integer> getNumberOfIncludedBags() {
        return numberOfIncludedBags;
    }

    /**
     * returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}

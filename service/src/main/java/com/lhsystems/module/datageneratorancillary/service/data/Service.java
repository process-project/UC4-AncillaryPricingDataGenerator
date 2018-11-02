package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Abstract class modelling all kinds of paid services and ancillaries.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "SERVICE_TYPE")
public abstract class Service {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The name of the service. */
    @Column(name = "NAME")
    private final String name;

    /**
     * The maximum number of times this ancillary can be booked during one
     * flight.
     */
    @Column(name = "MAXIMUM_CAPACITY")
    private final int maximumCapacity;

    /**
     * Instantiates a new service.
     */
    public Service() {
        name = null;
        maximumCapacity = 0;
    }

    /**
     * Instantiates a new service.
     *
     * @param paramName
     *            the name
     * @param paramMaximumCapacity
     *            the maximum capacity
     */
    public Service(final String paramName, final int paramMaximumCapacity) {
        name = paramName;
        maximumCapacity = paramMaximumCapacity;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public final String getName(){
        return name;
    }

    /**
     * Gets the maximum capacity.
     *
     * @return the maximum capacity
     */
    public final int getMaximumCapacity(){
        return maximumCapacity;
    }

    /**
     * Given how often this service is booked, return a price.
     *
     * @param number
     *            the number
     * @return the price
     */
    public abstract double getPrice(int number);

    /**
     * Gets the price of buying this service once.
     *
     * @return the price
     */
    public final double getPrice() {
        return getPrice(1);
    }

}


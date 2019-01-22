package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Data structure describing the customer who books.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "Customer")
public final class Customer {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The age of the customer. */
    @Column(name = "AGE")
    private final int age;

    /** The gender of the customer. */
    @Enumerated(EnumType.STRING)
    private final Gender gender;

    /** The purpose of traveling. */
    @Enumerated(EnumType.STRING)
    private final TravelType travelType;

    /** The satisfaction. */
    @Column(name = "SATISFACTION")
    private final int satisfaction;


    /**
     * Instantiates a new customer.
     *
     * @param paramAge
     *            the param age
     * @param paramGender
     *            the param gender
     * @param paramTravelType
     *            the param travel type
     * @param paramSatisfaction
     *            the param satisfaction
     */
    public Customer(final int paramAge, final Gender paramGender,
            final TravelType paramTravelType, final int paramSatisfaction) {
        age = paramAge;
        gender = paramGender;
        travelType = paramTravelType;
        satisfaction = paramSatisfaction;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the travel type.
     *
     * @return the travel type
     */
    public TravelType getTravelType() {
        return travelType;
    }
 
    /**
     * Gets the satisfaction.
     *
     * @return the satisfaction
     */
    public int getSatisfaction() {
        return satisfaction;
    }

}

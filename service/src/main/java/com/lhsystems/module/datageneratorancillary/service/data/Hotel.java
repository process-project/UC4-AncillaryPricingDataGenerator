package com.lhsystems.module.datageneratorancillary.service.data;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 * Data structure modeling a hotel booking as a service with a poisson
 * distributed price and an unlimited capacity.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class Hotel extends Service {

    /** The mean of the poisson distributed price. */
    @Column
    private final double pricePoissonMean;

    /**
     * Instantiates a new hotel service object.
     *
     * @param paramName
     *            the param name
     * @param paramPricePoissonMean
     *            the param price poisson mean
     */
    public Hotel(final String paramName, final double paramPricePoissonMean) {
        super(paramName, Integer.MAX_VALUE);
        pricePoissonMean = paramPricePoissonMean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final int number,
            final CoreBooking coreBooking) {
        return new PoissonDistribution(pricePoissonMean).sample();
    }

}

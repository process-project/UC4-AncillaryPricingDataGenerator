package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * A donation done by a customer while booking.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class Donation extends Service {

    /** The mean of the normal distributed value of a donation. */
    @Column
    private final double normalMean;

    /** The standard deviation of the normal distributed price. */
    @Column
    private final double normalStandardDeviation;

    /**
     * Instantiates a new donation.
     *
     * @param paramPriceNormalMean
     *            the mean of the normal distributed price
     * @param paramPriceNormalStandardDeviation
     *            the standard deviation of the normal distributed price
     * @param paramName
     *            the name of the donation
     */
    public Donation(final double paramPriceNormalMean,
            final double paramPriceNormalStandardDeviation, final String paramName) {
        super(paramName, Integer.MAX_VALUE);
        normalMean = paramPriceNormalMean;
        normalStandardDeviation = paramPriceNormalStandardDeviation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        return new NormalDistribution(
                normalMean,
                normalStandardDeviation).sample();
    }

    @Override
    public ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(final ServiceSerializedEntity.ServiceSerializedEntityBuilder
                                                                                                     serviceSerializedEntityBuilder) {
        return serviceSerializedEntityBuilder.setDonationFields(this);
    }

}

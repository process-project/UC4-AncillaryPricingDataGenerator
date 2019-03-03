package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.math3.distribution.PoissonDistribution;

/**
 * Data structure modeling an on board entertainment service with poisson
 * distributed price and unlimited capacity.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class EntertainmentOnBoard extends Service {

    /** The mean of the poisson distributed Price. */
    @Column
    private final double pricePoissonMean;

    /**
     * Instantiates a new entertainment on board object.
     *
     * @param paramPricePoissonMean
     *            the mean of the poisson distributed Price
     */
    public EntertainmentOnBoard(final double paramPricePoissonMean) {
        super("EntertainmentOnBoard", Integer.MAX_VALUE);
        pricePoissonMean = paramPricePoissonMean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        return new PoissonDistribution(pricePoissonMean).sample();
    }

    @Override
    public ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(final ServiceSerializedEntity.ServiceSerializedEntityBuilder
                                                                                                     serviceSerializedEntityBuilder) {
        return serviceSerializedEntityBuilder.setEntertainmentsFields(this);
    }

}

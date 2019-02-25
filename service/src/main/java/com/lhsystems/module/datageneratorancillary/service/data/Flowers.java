package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Data structure modeling a bouquet of flowers as a service with a fixed price
 * and a fixed capacity.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class Flowers extends Service {

    /** The fixed price of the flowers. */
    @Column
    private final double fixedPrice;

    /**
     * Instantiates a new flowers object.
     *
     * @param paramName
     *            the name
     * @param paramMaximumCapacity
     *            the maximum capacity
     * @param paramFixedPrice
     *            the fixed price
     */
    public Flowers(final String paramName, final int paramMaximumCapacity,
            final double paramFixedPrice) {
        super(paramName, paramMaximumCapacity);
        fixedPrice = paramFixedPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        return fixedPrice;
    }

    @Override
    public ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(final ServiceSerializedEntity.ServiceSerializedEntityBuilder
                                                                                                     serviceSerializedEntityBuilder) {
        return serviceSerializedEntityBuilder;
    }

}

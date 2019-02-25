package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Data structure modeling parking of vehicles as a service with a fixed price
 * and unlimited capacity.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class Parking extends Service {

    /** The fixed price of parking. */
    @Column
    private final double fixedPrice;

    /**
     * Instantiates a new parking object.
     *
     * @param paramMaximumCapacity
     *            the maximum capacity
     * @param paramFixedPrice
     *            the fixed price
     */
    public Parking(final int paramMaximumCapacity,
            final double paramFixedPrice) {
        super("parking", Integer.MAX_VALUE);
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

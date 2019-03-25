package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import java.util.List;

/**
 * Combines other serialized data objects.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class SerializedDataSummary {

    /** The serialized booking entity. */
    private final BookingSerializedEntity bookingSerializedEntity;

    /** The serialized service entities. */
    private final List<ServiceSerializedEntity> serviceSerializedEntities;

    /**
     * Instantiates a new serialized data summary.
     *
     * @param bookingSerializedEntityParam
     *            the booking serialized entity
     * @param serviceSerializedEntitiesParam
     *            the service serialized entities
     */
    public SerializedDataSummary(final BookingSerializedEntity bookingSerializedEntityParam,
            final List<ServiceSerializedEntity> serviceSerializedEntitiesParam) {
        bookingSerializedEntity = bookingSerializedEntityParam;
        serviceSerializedEntities = serviceSerializedEntitiesParam;
    }

    /**
     * Gets the serialized booking entity.
     *
     * @return the booking serialized entity
     */
    public BookingSerializedEntity getBookingSerializedEntity() {
        return bookingSerializedEntity;
    }

    /**
     * Gets the list of serialized service entities.
     *
     * @return the service serialized entities
     */
    public List<ServiceSerializedEntity> getServiceSerializedEntities() {
        return serviceSerializedEntities;
    }
}

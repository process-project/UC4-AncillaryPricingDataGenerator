package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import java.util.List;

public class SerializedDataSummary {
    private final BookingSerializedEntity bookingSerializedEntity;
    private final List<ServiceSerializedEntity> serviceSerializedEntities;

    public SerializedDataSummary(final BookingSerializedEntity bookingSerializedEntityParam,
                                 final List<ServiceSerializedEntity> serviceSerializedEntitiesParam) {
        this.bookingSerializedEntity = bookingSerializedEntityParam;
        this.serviceSerializedEntities = serviceSerializedEntitiesParam;
    }

    public BookingSerializedEntity getBookingSerializedEntity() {
        return bookingSerializedEntity;
    }

    public List<ServiceSerializedEntity> getServiceSerializedEntities() {
        return serviceSerializedEntities;
    }
}

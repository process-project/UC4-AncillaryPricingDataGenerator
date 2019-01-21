package com.lhsystems.module.datageneratorancillary.service.serializer;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.BookingSerializedEntity;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.SerializedDataSummary;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoreBookingSerializer {


    public final void generateFlattenData(final List<Booking> bookings) {
        final List<SerializedDataSummary> serializedDataSummaries = createSerializedData(bookings);
        final List<BookingSerializedEntity> bookingSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .map(SerializedDataSummary::getBookingSerializedEntity)
                        .collect(Collectors.toList());
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .flatMap(data -> data.getServiceSerializedEntities().stream())
                        .collect(Collectors.toList());
    }

    private List<SerializedDataSummary> createSerializedData(final List<Booking> bookings){
        return bookings.stream().map(this::createSerializedEntity).collect(Collectors.toList());
    }

    private SerializedDataSummary createSerializedEntity(final Booking booking) {
        final BookingSerializedEntity bookingSerializedEntity = createBookingSerializedEntity(booking);
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                booking.getServiceOrders().stream().map(serviceOrder -> createServiceSerializedEntity(serviceOrder, bookingSerializedEntity.getId())).collect(Collectors.toList());

        return new SerializedDataSummary(bookingSerializedEntity, serviceSerializedEntities);
    }

    private BookingSerializedEntity createBookingSerializedEntity(final Booking booking) {
        return new BookingSerializedEntity.BookingSerializedEntityBuilder()
                .setCoreBookingFields(booking.getCoreBooking())
                .setCustomerEntities(booking.getCustomer())
                .setFlightFields(booking.getCoreBooking().getFlight())
                .setRouteFields(booking.getCoreBooking().getFlight().getRoute())
                .setTariffFields(booking.getCoreBooking().getTariff())
                .build();
    }

    private ServiceSerializedEntity createServiceSerializedEntity(final ServiceOrder serviceOrder, final UUID bookingSerializedEntityId) {
        return new ServiceSerializedEntity.ServiceSerializedEntityBuilder(bookingSerializedEntityId)
                .setServiceOrderFields(serviceOrder)
                .setServiceFields(serviceOrder.getService())
                .build();
    }
}

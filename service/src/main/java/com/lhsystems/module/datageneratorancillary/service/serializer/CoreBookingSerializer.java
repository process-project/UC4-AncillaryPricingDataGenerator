package com.lhsystems.module.datageneratorancillary.service.serializer;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.BookingSerializedEntity;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.SerializedDataSummary;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * The type Core booking serializer.
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public class CoreBookingSerializer {

    /** Save flattered data. */
    private final CoreBookingSerializerSaver coreBookingSerializerSaver;


    /**
     * Instantiates a new Core booking serializer.
     *
     * @param coreBookingSerializerSaverParam the service responsible for saving serialized data
     */
    public CoreBookingSerializer(final CoreBookingSerializerSaver coreBookingSerializerSaverParam) {
        this.coreBookingSerializerSaver = coreBookingSerializerSaverParam;
    }

    /**
     * Generate flatten data and then save it.
     *
     * @param bookings the bookings
     */
    public final void generateFlattenData(final List<Booking> bookings) {
        final List<SerializedDataSummary> serializedDataSummaries = createSerializedData(bookings);
        coreBookingSerializerSaver.saveSerializedEntities(serializedDataSummaries);
    }

    /**
     * Create serialized data for each booking.
     *
     * @param bookings
     *          the generated bookings
     * @return
     *      the list of serialized entities
     */
    private List<SerializedDataSummary> createSerializedData(final List<Booking> bookings){
        return bookings.stream().map(this::createSerializedEntity).collect(Collectors.toList());
    }

    /**
     * Create serialized entity for one booking.
     *
     * @param booking
     *          the current booking
     * @return the serialized data for this booking
     */
    private SerializedDataSummary createSerializedEntity(final Booking booking) {
        final BookingSerializedEntity bookingSerializedEntity = createBookingSerializedEntity(booking);
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                booking.getServiceOrders().stream().map(serviceOrder -> createServiceSerializedEntity(serviceOrder, bookingSerializedEntity.getId())).collect(Collectors.toList());

        return new SerializedDataSummary(bookingSerializedEntity, serviceSerializedEntities);
    }

    /**
     * Create booking serialized entity.
     *
     * @param booking
     *          the current booking
     * @return the booking serialized entity
     */
    private BookingSerializedEntity createBookingSerializedEntity(final Booking booking) {
        return new BookingSerializedEntity.BookingSerializedEntityBuilder()
                .setCoreBookingFields(booking.getCoreBooking())
                .setCustomerEntities(booking.getCustomer())
                .setFlightFields(booking.getCoreBooking().getFlight())
                .setRouteFields(booking.getCoreBooking().getFlight().getRoute())
                .setTariffFields(booking.getCoreBooking().getTariff())
                .setProductFields(booking.getCoreBooking().getTariff().getProduct())
                .build();
    }

    /**
     * Create service serialized entity service serialized entity.
     *
     * @param serviceOrder
     *          the current service order
     * @param bookingSerializedEntityId
     *          the serialized booking entity id
     * @return the service serialized entity
     */
    private ServiceSerializedEntity createServiceSerializedEntity(final ServiceOrder serviceOrder, final UUID bookingSerializedEntityId) {
        return new ServiceSerializedEntity.ServiceSerializedEntityBuilder(bookingSerializedEntityId)
                .setServiceOrderFields(serviceOrder)
                .setServiceFields(serviceOrder.getService())
                .build();
    }
}

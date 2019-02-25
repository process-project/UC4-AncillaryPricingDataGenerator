package com.lhsystems.module.datageneratorancillary.service.serializer;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.BookingSerializedEntity;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.SerializedDataSummary;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * The core booking saver.
 *
 * @author REJ
 * @version $Revision : 1.10 $
 */
@Service
public class CoreBookingSerializerSaver {


    /**
     * Basic constructor.
     */
    public CoreBookingSerializerSaver() {
    }

    /**
     * Save serialized entities.
     *
     * @param serializedDataSummaries the serialized data
     */
    void saveSerializedEntities(final List<SerializedDataSummary> serializedDataSummaries) {
        saveBookingSerializedEntities(serializedDataSummaries);
        saveServiceSerializedEntities(serializedDataSummaries);
    }

    /**
     * Save booking serialized entities.
     *
     * @param serializedDataSummaries
     *      the list of serialized data summaries
     */
    private void saveBookingSerializedEntities(final List<SerializedDataSummary> serializedDataSummaries) {
        final List<BookingSerializedEntity> bookingSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .map(SerializedDataSummary::getBookingSerializedEntity)
                        .collect(Collectors.toList());

        CsvFileSaver.saveEntitiesList(bookingSerializedEntities, "bookings.csv", BookingSerializedEntity.class);
    }

    /**
     * Save service serialized entities.
     *
     * @param serializedDataSummaries he list of serialized data summaries
     */
    private static void saveServiceSerializedEntities(final List<SerializedDataSummary> serializedDataSummaries) {
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .flatMap(data -> data.getServiceSerializedEntities().stream())
                        .collect(Collectors.toList());
        CsvFileSaver.saveEntitiesList(serviceSerializedEntities, "services.csv", ServiceSerializedEntity.class);
    }
}

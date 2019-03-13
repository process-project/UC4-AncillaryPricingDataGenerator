package com.lhsystems.module.datageneratorancillary.service.serializer;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.BookingSerializedEntity;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.SerializedDataSummary;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import static com.lhsystems.module.datageneratorancillary.service.serializer.CsvConstants.BOOKING_CSV_FILE_NAME;
import static com.lhsystems.module.datageneratorancillary.service.serializer.CsvConstants.SERVICE_CSV_FILE_NAME;

/**
 * The core booking saver.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public class CoreBookingSerializerSaver {


    /**
     * Basic constructor.
     */
    public CoreBookingSerializerSaver() { }

    /**
     * Save serialized entities.
     *
     * @param serializedDataSummaries
     *          the serialized data
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

        //CsvLocalFileSaver.saveEntitiesList(bookingSerializedEntities, BOOKING_CSV_FILE_NAME, BookingSerializedEntity.class);
        HadoopFileSaver.saveEntitiesList(bookingSerializedEntities, BOOKING_CSV_FILE_NAME, BookingSerializedEntity.class);
    }

    /**
     * Save service serialized entities.
     *
     * @param serializedDataSummaries
     *          The list of serialized data summaries
     */
    private static void saveServiceSerializedEntities(final List<SerializedDataSummary> serializedDataSummaries) {
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .flatMap(data -> data.getServiceSerializedEntities().stream())
                        .collect(Collectors.toList());
        //CsvLocalFileSaver.saveEntitiesList(serviceSerializedEntities, SERVICE_CSV_FILE_NAME, ServiceSerializedEntity.class);
        HadoopFileSaver.saveEntitiesList(serviceSerializedEntities, SERVICE_CSV_FILE_NAME, ServiceSerializedEntity.class);
    }
}

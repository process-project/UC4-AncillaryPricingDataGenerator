package com.lhsystems.module.datageneratorancillary.service.serializer;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.BookingSerializedEntity;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.SerializedDataSummary;
import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
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

    private final String bookingName;
    private final String serviceName;
    private final Boolean saveLocally;

    /**
     * Basic constructor.
     */
    public CoreBookingSerializerSaver(@Value("${BOOKING_FILENAME:#{null}}") final String bookingFilenameParam,
                                      @Value("${SERVICE_FILENAME:#{null}}") final String serviceFilenameParam,
                                      @Value("${SAVE_LOCALLY}") final String saveLocallyParam) {
        this.bookingName = bookingFilenameParam == null ? BOOKING_CSV_FILE_NAME : bookingFilenameParam;
        this.serviceName = serviceFilenameParam == null ? SERVICE_CSV_FILE_NAME : serviceFilenameParam;
        this.saveLocally = Boolean.valueOf(saveLocallyParam);
    }

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

        if(saveLocally) {
            CsvLocalFileSaver.saveEntitiesList(bookingSerializedEntities, bookingName, BookingSerializedEntity.class);
        } else {
            HadoopFileSaver.saveEntitiesList(bookingSerializedEntities, bookingName, BookingSerializedEntity.class);
        }
    }

    /**
     * Save service serialized entities.
     *
     * @param serializedDataSummaries
     *          The list of serialized data summaries
     */
    private void saveServiceSerializedEntities(final List<SerializedDataSummary> serializedDataSummaries) {
        final List<ServiceSerializedEntity> serviceSerializedEntities =
                serializedDataSummaries
                        .stream()
                        .flatMap(data -> data.getServiceSerializedEntities().stream())
                        .collect(Collectors.toList());
        if(saveLocally) {
            CsvLocalFileSaver.saveEntitiesList(serviceSerializedEntities, serviceName, ServiceSerializedEntity.class);
        } else {
            HadoopFileSaver.saveEntitiesList(serviceSerializedEntities, serviceName, ServiceSerializedEntity.class);
        }
    }
}

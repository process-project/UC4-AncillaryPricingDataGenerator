package com.lhsystems.module.datageneratorancillary.service.generator;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.starter.BookingGeneratorStarter;
import com.lhsystems.module.datageneratorancillary.service.generator.starter.FlightGeneratorStarter;
import com.lhsystems.module.datageneratorancillary.service.serializer.CoreBookingSerializer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * The booking generator. It generates entities and saves them in a batch .
 *
 * @author REJ
 * @version $Revision : 1.10 $
 */
@Service
public class BatchBookingGenerator {
    /** Logger.*/
    private static final Logger log = LoggerFactory.getLogger(BatchBookingGenerator.class);

    /** Starts serializing core booking entities */
    private final CoreBookingSerializer coreBookingSerializer;

    /** Starts generating booking entities. */
    private final BookingGeneratorStarter bookingGeneratorStarter;

    /** Starts generating flight entities. */
    private final FlightGeneratorStarter flightGeneratorStarter;


    /**
     * Instantiates a new Batch booking generator.
     *
     * @param flightGeneratorStarterParam
     *          the flight generator starter
     * @param coreBookingSerializerParam
     *          the core booking serializer
     * @param bookingGeneratorStarterParam
     *          the booking generator starter
     */
    public BatchBookingGenerator(final FlightGeneratorStarter flightGeneratorStarterParam,
                                 final CoreBookingSerializer coreBookingSerializerParam,
                                 final BookingGeneratorStarter bookingGeneratorStarterParam) {
        bookingGeneratorStarter = bookingGeneratorStarterParam;
        coreBookingSerializer = coreBookingSerializerParam;
        flightGeneratorStarter = flightGeneratorStarterParam;

    }

    /**
     * Generate bookings in batch and append them into file after every batch.
     *
     * @param routes                 the routes generated previously
     * @param tariffs                the tariffs generated previously
     * @param generatorConfiguration the generator configuration
     */
    public void generateBookingsInBatch(final List<Route> routes,
                                        final List<Tariff> tariffs,
                                        final GeneratorConfiguration generatorConfiguration){
        final long numberOfFlights = generatorConfiguration.getFlightConfiguration().getNumberFlight();
        final long oneBatch = Math.min(numberOfFlights, 1_000_000);
        long currentNumberOfGeneratedBookings = 0;
        while (currentNumberOfGeneratedBookings < numberOfFlights) {
            final long startTime = System.currentTimeMillis();
            currentNumberOfGeneratedBookings += oneBatch;
            final long leftEntities = Math.max(0, (numberOfFlights - currentNumberOfGeneratedBookings));

            log.info("Started generating booking entities. Range from: " + (currentNumberOfGeneratedBookings-oneBatch)
                    + " to: " + Math.min(currentNumberOfGeneratedBookings, numberOfFlights) + " left: "  + leftEntities);

            final List<Flight> flights = flightGeneratorStarter.generateFlightsEntities(
                    generatorConfiguration.getFlightConfiguration(),
                    tariffs,
                    routes,
                    oneBatch);

            final List<Booking> bookings = bookingGeneratorStarter.generateBookingEntities(
                    flights,
                    generatorConfiguration.getCustomerConfiguration(),
                    generatorConfiguration.getCoreBookingConfiguration(),
                    generatorConfiguration.getServiceOrderConfiguration());

            coreBookingSerializer.generateFlattenData(bookings);
            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("Batch of entities generated and saved successfully. It takes " + elapsedTime + " ms");
        }
    }
}

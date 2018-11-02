package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceSelection;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BookingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.BookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.CoreBookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.ServiceSelectionRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Starts generating booking entities and saves them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class BookingGeneratorStarter {

    /** Repository for storing complete bookings. */
    private final BookingRepository bookingRepository;

    /** Repository for core bookings. */
    private final CoreBookingRepository coreBookingRepository;

    /** Repository for saving services. */
    private final ServiceSelectionRepository serviceSelectionRepository;

    /**
     * Instantiates a new booking generator starter.
     *
     * @param bookingRepositoryParam
     *            the complete booking repository
     * @param serviceSelectionRepositoryParam
     *            the service repository
     * @param coreBookingRepositoryParam
     *            the simple booking repository
     */
    @Autowired
    public BookingGeneratorStarter(
            final BookingRepository bookingRepositoryParam,
            final ServiceSelectionRepository serviceSelectionRepositoryParam,
            final CoreBookingRepository coreBookingRepositoryParam) {
        bookingRepository = bookingRepositoryParam;
        serviceSelectionRepository = serviceSelectionRepositoryParam;
        coreBookingRepository = coreBookingRepositoryParam;
    }

    /**
     * Generates booking entities and save all associated entities.
     *
     * @param flights
     *            the flights to be used for generation
     * @param bookingConfiguration
     *            the booking configuration
     * @return a list of complete bookings
     */
    public List<Booking> generateBookingEntities(
            final List<Flight> flights,
            final BookingConfiguration bookingConfiguration) {
        final BookingGenerator bookingGenerator = new BookingGenerator(
                flights,
                bookingConfiguration);
        final List<Booking> bookings = bookingGenerator.generateList(
                bookingConfiguration.getNumberBookings());
        final List<ServiceSelection> serviceSelections = bookings.stream().map(
                booking -> booking.getServiceSelection()).collect(
                        Collectors.toList());
        final List<CoreBooking> coreBookings = bookings.stream().map(
                booking -> booking.getCoreBooking()).collect(
                        Collectors.toList());
        coreBookingRepository.save(coreBookings);
        serviceSelectionRepository.save(serviceSelections);
        bookingRepository.save(bookings);
        return bookings;
    }

}

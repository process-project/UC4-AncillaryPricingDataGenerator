package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.SeatSelection;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BookingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BookingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggageSelectionRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.BookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.CoreBookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatSelectionRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Starts generating booking entities and saves them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public final class BookingGeneratorStarter {

    /** Repository for storing baggage selections. */
    private final BaggageSelectionRepository baggageSelectionRepository;

    /** Repository for storing complete bookings. */
    private final BookingRepository bookingRepository;

    /** Repository for storing seat selections. */
    private final SeatSelectionRepository seatSelectionRepository;

    /** Repository for simple bookings. */
    private final CoreBookingRepository coreBookingRepository;

    /**
     * Instantiates a new booking generator starter.
     *
     * @param baggageSelectionRepositoryParam
     *            the baggage selection repository
     * @param bookingRepositoryParam
     *            the complete booking repository
     * @param seatSelectionRepositoryParam
     *            the seat selection repository
     * @param coreBookingRepositoryParam
     *            the simple booking repository
     */
    @Autowired
    public BookingGeneratorStarter(
            final BaggageSelectionRepository baggageSelectionRepositoryParam,
            final BookingRepository bookingRepositoryParam,
            final SeatSelectionRepository seatSelectionRepositoryParam,
            final CoreBookingRepository coreBookingRepositoryParam) {
        baggageSelectionRepository = baggageSelectionRepositoryParam;
        bookingRepository = bookingRepositoryParam;
        seatSelectionRepository = seatSelectionRepositoryParam;
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
        final List<BaggageSelection> baggageSelections = bookings.stream().map(booking -> booking.getBaggageSelection()).collect(Collectors.toList());
        final List<CoreBooking> coreBookings = bookings.stream().map(
                booking -> booking.getCoreBooking()).collect(
                        Collectors.toList());
        final List<SeatSelection> seatSelections = bookings.stream().map(
                booking -> booking.getSeatSelection()).collect(
                        Collectors.toList());
        coreBookingRepository.save(coreBookings);
        baggageSelectionRepository.save(baggageSelections);
        seatSelectionRepository.save(seatSelections);
        bookingRepository.save(bookings);
        return bookings;
    }

}

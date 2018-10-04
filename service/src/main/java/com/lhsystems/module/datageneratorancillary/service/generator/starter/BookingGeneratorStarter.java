package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.SeatSelection;
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
    private final BookingRepository completeBookingRepository;

    /** Repository for storing seat selections. */
    private final SeatSelectionRepository seatSelectionRepository;

    /** Repository for simple bookings. */
    private final CoreBookingRepository simpleBookingRepository;

    /**
     * Instantiates a new booking generator starter.
     *
     * @param baggageSelectionRepositoryParam
     *            the baggage selection repository
     * @param completeBookingRepositoryParam
     *            the complete booking repository
     * @param seatSelectionRepositoryParam
     *            the seat selection repository
     * @param simpleBookingRepositoryParam
     *            the simple booking repository
     */
    @Autowired
    public BookingGeneratorStarter(
            final BaggageSelectionRepository baggageSelectionRepositoryParam,
            final BookingRepository completeBookingRepositoryParam,
            final SeatSelectionRepository seatSelectionRepositoryParam,
            final CoreBookingRepository simpleBookingRepositoryParam) {
        baggageSelectionRepository = baggageSelectionRepositoryParam;
        completeBookingRepository = completeBookingRepositoryParam;
        seatSelectionRepository = seatSelectionRepositoryParam;
        simpleBookingRepository = simpleBookingRepositoryParam;
    }

    /**
     * Generates booking entities and save all associated entities.
     *
     * @param numberBookings
     *            the number bookings
     * @param flights
     *            flights, that can be booked
     * @return a list of complete bookings
     */
    public List<Booking> generateBookingEntities(
            final int numberBookings, final List<Flight> flights) {
        final BookingGenerator bookingGenerator = new BookingGenerator(
                flights);
        final List<Booking> bookings = bookingGenerator.generateList(
                numberBookings);
        final List<BaggageSelection> baggageSelections = bookings.stream().map(
                booking -> booking.getBaggageSelection()).collect(
                        Collectors.toList());
        final List<CoreBooking> coreBookings = bookings.stream().map(
                booking -> booking.getCoreBooking()).collect(
                        Collectors.toList());
        final List<SeatSelection> seatSelections = bookings.stream().map(
                booking -> booking.getSeatSelection()).collect(
                        Collectors.toList());
        simpleBookingRepository.save(coreBookings);
        baggageSelectionRepository.save(baggageSelections);
        seatSelectionRepository.save(seatSelections);
        completeBookingRepository.save(bookings);
        return bookings;
    }

}

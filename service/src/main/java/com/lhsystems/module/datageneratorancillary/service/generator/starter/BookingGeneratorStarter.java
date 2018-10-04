package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;
import com.lhsystems.module.datageneratorancillary.service.data.CompleteBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.SeatSelection;
import com.lhsystems.module.datageneratorancillary.service.data.SimpleBooking;
import com.lhsystems.module.datageneratorancillary.service.generator.core.CompleteBookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggageSelectionRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.CompleteBookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.SeatSelectionRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.SimpleBookingRepository;

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
    private final CompleteBookingRepository completeBookingRepository;

    /** Repository for storing seat selections. */
    private final SeatSelectionRepository seatSelectionRepository;

    /** Repository for simple bookings. */
    private final SimpleBookingRepository simpleBookingRepository;

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
            final CompleteBookingRepository completeBookingRepositoryParam,
            final SeatSelectionRepository seatSelectionRepositoryParam,
            final SimpleBookingRepository simpleBookingRepositoryParam) {
        baggageSelectionRepository = baggageSelectionRepositoryParam;
        completeBookingRepository = completeBookingRepositoryParam;
        seatSelectionRepository = seatSelectionRepositoryParam;
        simpleBookingRepository = simpleBookingRepositoryParam;
    }

    /**
     * Generates booking entities and save all associated .
     *
     * @param numberBookings
     *            the number bookings
     * @return a list of complete bookings
     */
    public List<CompleteBooking> generateBookingEntities(
            final int numberBookings, final List<Flight> flights) {
        final CompleteBookingGenerator completeBookingGenerator = new CompleteBookingGenerator(
                flights);
        final List<CompleteBooking> completeBookings = completeBookingGenerator.generateList(
                numberBookings);
        final List<BaggageSelection> baggageSelections = completeBookings.stream().map(booking -> booking.getBaggageSelection()).collect(Collectors.toList());
        final List<SimpleBooking> simpleBookings = completeBookings.stream().map(
                booking -> booking.getSimpleBooking()).collect(
                        Collectors.toList());
        final List<SeatSelection> seatSelections = completeBookings.stream().map(
                booking -> booking.getSeatSelection()).collect(
                        Collectors.toList());
        simpleBookingRepository.save(simpleBookings);
        baggageSelectionRepository.save(baggageSelections);
        seatSelectionRepository.save(seatSelections);
        completeBookingRepository.save(completeBookings);
        return completeBookings;
    }

}

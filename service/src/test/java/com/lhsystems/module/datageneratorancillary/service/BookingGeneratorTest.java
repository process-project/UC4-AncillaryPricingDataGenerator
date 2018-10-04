package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatSelection;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BookingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BookingGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * test <code>BookingGenerator</code>.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingGeneratorTest {

    private Airport airport1;

    private Airport airport2;

    private BaggageSize baggageSize;

    private BaggageLimits baggageLimits;

    private BaggagePricing baggagePricing;

    private BaggageClass baggageClass;

    private Compartment compartment;

    private Product product;

    private SeatGroup seatGroup;

    private Tariff tariff;

    private SeatingModel seatingModel;

    private Flight flight;

    @Before
    public final void setUp() {
        airport1 = new Airport(
                "TAC",
                "Test Airport Continental",
                Market.CONTINENTAL);
        airport2 = new Airport("TAD", "Test Airport Domestic", Market.DOMESTIC);
        baggageSize = new BaggageSize(3, 3, 3, 3);
        baggageLimits = new BaggageLimits(baggageSize, 3, 3);
        baggagePricing = new BaggagePricing(3, 3, 3);
        baggageClass = new BaggageClass(
                "baggageClass",
                baggageLimits,
                baggagePricing);
        compartment = new Compartment('N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        product = new Product(
                "product",
                compartment,
                baggageClasses,
                includedBags);
        seatGroup = new SeatGroup("seatGroup", 1, 1);
        final List<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
        seatingModel = new SeatingModel(seatGroups);
        tariff = new Tariff(3, seatingModel, product, Market.CONTINENTAL);
        final List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        flight = new Flight(
                1,
                LocalDateTime.of(2018, 5, 5, 12, 0),
                new Route(airport1, airport2),
                tariffs);
    }

    @Test
    public final void testGenerateBookings() {
        final List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        final BookingConfiguration bookingConfiguration = new BookingConfiguration();
        bookingConfiguration.setMaximumNumberBags(1);
        bookingConfiguration.setMinimumNumberBags(1);
        bookingConfiguration.setMinimumNumberPassengers(1);
        bookingConfiguration.setMaximumNumberPassengers(1);
        final BookingGenerator bookingGenerator = new BookingGenerator(
                flights,
                bookingConfiguration);
        final List<Booking> bookings = bookingGenerator.generateList(1);
        final CoreBooking coreBooking = new CoreBooking(1, flight, 1, tariff);
        final Map<SeatGroup, Integer> chosenSeats = new HashMap<>();
        chosenSeats.put(seatGroup, 1);
        final SeatSelection seatSelection = new SeatSelection(chosenSeats, 1);
        final Map<BaggageClass, Integer> chosenBaggage = new HashMap<>();
        chosenBaggage.put(baggageClass, 1);
        final BaggageSelection baggageSelection = new BaggageSelection(
                chosenBaggage,
                1);
        final Booking testBooking = new Booking(
                coreBooking,
                seatSelection,
                baggageSelection);
        assertEquals(
                testBooking.getBaggageSelection().getChosenBaggage(),
                bookings.get(0).getBaggageSelection().getChosenBaggage());
        assertEquals(
                testBooking.getSeatSelection().getChosenSeats(),
                bookings.get(0).getSeatSelection().getChosenSeats());
        assertEquals(
                testBooking.getCoreBooking().getFlight(),
                bookings.get(0).getCoreBooking().getFlight());
        assertEquals(
                testBooking.getCoreBooking().getTariff(),
                bookings.get(0).getCoreBooking().getTariff());
    }
}

package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceSelection;
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
                1,
                baggageLimits,
                baggagePricing);
        compartment = new Compartment('N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        seatGroup = new SeatGroup("seatGroup", 1, 1);
        final List<Service> services = new ArrayList<>();
        services.add(seatGroup);
        services.addAll(baggageClasses);
        product = new Product(
                "product",
                compartment,
                services,
                includedBags);
        tariff = new Tariff(3, product, Market.CONTINENTAL);
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
        final Map<Service, Integer> chosenService = new HashMap<>();
        chosenService.put(baggageClass, 1);
        chosenService.put(seatGroup, 1);
        final Map<Service, Integer> daysBeforeDeparture = new HashMap<>();
        daysBeforeDeparture.put(baggageClass, 1);
        daysBeforeDeparture.put(seatGroup, 1);
        final Map<Service, Double> prices = new HashMap<>();
        prices.put(baggageClass, 1.0);
        prices.put(seatGroup, 1.0);
        final ServiceSelection serviceSelection = new ServiceSelection(
                chosenService,
                prices,
                daysBeforeDeparture);
        final Booking testBooking = new Booking(
                coreBooking,
                serviceSelection);
        assertEquals(
                testBooking.getServiceSelection(),
                bookings.get(0).getServiceSelection());
        assertEquals(
                testBooking.getCoreBooking().getFlight(),
                bookings.get(0).getCoreBooking().getFlight());
        assertEquals(
                testBooking.getCoreBooking().getTariff(),
                bookings.get(0).getCoreBooking().getTariff());
    }
}

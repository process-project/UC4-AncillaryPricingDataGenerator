package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Airport;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Customer;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Gender;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.data.TravelType;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ServiceOrderConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.ServiceOrderGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ServiceOrderGeneratorTest {

    private static final int SAMPLE_SIZE = 50;

    private static final int MINIMUM_NUMBER_BAGS = 1;

    private static final int MAXIMUM_NUMBER_BAGS = 3;

    private CoreBooking coreBooking;


    @Before
    public void setUp() {
        final BaggageSize baggageSize = new BaggageSize(3, 3, 3, 3);
        final BaggageLimits baggageLimits = new BaggageLimits(
                baggageSize,
                3,
                3);
        final BaggagePricing baggagePricing = new BaggagePricing(3, 3, 3);
        final BaggageClass baggageClass = new BaggageClass(
                "baggageClass",
                1,
                baggageLimits,
                baggagePricing);
        final Compartment compartment = new Compartment('N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        final SeatGroup seatGroup = new SeatGroup("seatGroup", 1, 1);
        final List<Service> services = new ArrayList<>();
        services.add(seatGroup);
        services.addAll(baggageClasses);
        final Product product = new Product(
                "product",
                compartment,
                services,
                includedBags);
        final Tariff tariff = new Tariff(3, product, Market.CONTINENTAL);
        final List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        final Route route = new Route(new Airport("TAD","Test Airport Domestic", Market.DOMESTIC), new Airport("TAI","Test Airport Intercontinental", Market.INTERCONTINENTAL));
        final Flight flight = new Flight(1L, LocalDateTime.of(2018, 11, 11, 6, 6), route, tariffs);
        final Customer customer = new Customer(
                40,
                Gender.FEMALE,
                TravelType.BUSINESS,
                3);
        coreBooking = new CoreBooking(1, flight, 3, tariff, customer);
    }

    @Test
    public final void testGenerateOrders() {
        final ServiceOrderConfiguration serviceOrderConfiguration = new ServiceOrderConfiguration();
        serviceOrderConfiguration.setMaximumNumberBags(MAXIMUM_NUMBER_BAGS);
        serviceOrderConfiguration.setMinimumNumberBags(MINIMUM_NUMBER_BAGS);
        final ServiceOrderGenerator generator = new ServiceOrderGenerator(
                serviceOrderConfiguration);
        List<ServiceOrder> serviceOrders = new ArrayList<>();

        for (int testCounter = 0; testCounter < SAMPLE_SIZE; testCounter++) {
            serviceOrders = generator.generateOrders(coreBooking);
            assertTrue(checkServices(serviceOrders));
        }
    }

    private boolean checkServices(final List<ServiceOrder> serviceOrders){
        int numberOfBookedBags = 0;
        int numberOfSeats = 0;
        for (final ServiceOrder serviceOrder: serviceOrders){
            if (serviceOrder.getService().getClass() == SeatGroup.class){
                numberOfSeats += serviceOrder.getCount();
            }
            if (serviceOrder.getService().getClass() == BaggageClass.class) {
                numberOfBookedBags += serviceOrder.getCount();
            }
        }
        return (numberOfSeats == coreBooking.getNumberPassengers()
                && numberOfBookedBags >= coreBooking.getNumberPassengers()
                * MINIMUM_NUMBER_BAGS
                && numberOfBookedBags <= coreBooking.getNumberPassengers()
                * MAXIMUM_NUMBER_BAGS);

    }
}

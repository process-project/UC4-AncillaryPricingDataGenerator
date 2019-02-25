package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.*;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.serializer.CoreBookingSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Starts generating all entities in the proper order.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class GeneratorStarter {

    /** Starts generating baggage entities. */
    private final BaggageGeneratorStarter baggageGeneratorStarter;

    /** Starts generating booking entities. */
    private final BookingGeneratorStarter bookingGeneratorStarter;

    /** Starts generating flight entities. */
    private final FlightGeneratorStarter flightGeneratorStarter;

    /** Starts generating products entities. */
    private final ProductGeneratorStarter productGeneratorStarter;

    /** Starts generating routes and airports. */
    private final RoutesGeneratorStarter routesGeneratorStarter;

    /** Starts generating seat entities. */
    private final SeatingGeneratorStarter seatingGeneratorStarter;

    /**
     * Starts generating additional service entities besides seatGroups and
     * baggage.
     */
    private final ServiceGeneratorStarter serviceGeneratorStarter;

    /** Starts generating tariff entities. */
    private final TariffGeneratorStarter tariffGeneratorStarter;

    /** Starts serializing core booking entities. */
    private final CoreBookingSerializer coreBookingSerializer;

    /**
     * Instantiates a new Generator starter.
     *
     * @param productGeneratorStarterParam
     *            the product generator starter
     * @param baggageGeneratorStarterParam
     *            the baggage generator starter
     * @param seatingGeneratorStarterParam
     *            the seating generator starter
     * @param serviceGeneratorStarterParam
     *            the service generator starter
     * @param tariffGeneratorStarterParam
     *            the tariff generator starter
     * @param flightGeneratorStarterParam
     *            the flight generator starter
     * @param routesGeneratorStarterParam
     *            the routes generator starter
     * @param bookingGeneratorStarterParam
     *            the booking generator starter
     * @param coreBookingSerializer
     *            the component responsible for serializing data
     */
    @Autowired
    public GeneratorStarter(final ProductGeneratorStarter productGeneratorStarterParam,
            final BaggageGeneratorStarter baggageGeneratorStarterParam,
            final SeatingGeneratorStarter seatingGeneratorStarterParam,
            final ServiceGeneratorStarter serviceGeneratorStarterParam,
            final TariffGeneratorStarter tariffGeneratorStarterParam,
            final FlightGeneratorStarter flightGeneratorStarterParam,
            final RoutesGeneratorStarter routesGeneratorStarterParam,
            final BookingGeneratorStarter bookingGeneratorStarterParam,
            final CoreBookingSerializer coreBookingSerializerParam) {
        productGeneratorStarter = productGeneratorStarterParam;
        baggageGeneratorStarter = baggageGeneratorStarterParam;
        seatingGeneratorStarter = seatingGeneratorStarterParam;
        serviceGeneratorStarter = serviceGeneratorStarterParam;
        tariffGeneratorStarter = tariffGeneratorStarterParam;
        flightGeneratorStarter = flightGeneratorStarterParam;
        routesGeneratorStarter = routesGeneratorStarterParam;
        bookingGeneratorStarter = bookingGeneratorStarterParam;
        coreBookingSerializer = coreBookingSerializerParam;
    }

    /**
     * Starts generating flight data in proper order.
     *
     * @param generatorConfiguration
     *            the generator configuration
     * @param ssimLines
     *            the ssim lines
     * @param compartments
     *            compartments the products belong to
     */
    public void generateData(
            final GeneratorConfiguration generatorConfiguration,
            final List<String> ssimLines,
            final List<Compartment> compartments) {
        final List<BaggageClass> baggageClasses = baggageGeneratorStarter.generateBaggageEntities(
                generatorConfiguration.getBaggageClassConfiguration(),
                generatorConfiguration.getBaggageLimitsConfiguration(),
                generatorConfiguration.getBaggagePricingConfiguration(),
                generatorConfiguration.getBaggageSizeConfiguration());
        final List<SeatGroup> seatGroups = seatingGeneratorStarter.generateSeatGroupEntities(
                generatorConfiguration.getSeatGroupConfiguration());
        final List<Service> services = serviceGeneratorStarter.generateServiceEntities(
                generatorConfiguration.getServiceConfiguration());
        for (final BaggageClass baggageClass : baggageClasses) {
            services.add(baggageClass);
        }
        for (final SeatGroup seatGroup : seatGroups) {
            services.add(seatGroup);
        }
        final List<Service> services = new ArrayList<>();
        services.addAll(baggageClasses);
        services.addAll(seatGroups);
        final List<Product> products = productGeneratorStarter.generateProductEntities(
                compartments,
                services,
                generatorConfiguration.getProductConfiguration());
        final List<Tariff> tariffs = tariffGeneratorStarter.generateTariffsEntities(
                products,
                generatorConfiguration.getTariffConfiguration());

        final List<Market> markets = getAvailableMarkets(tariffs);

        final List<Route> routes = routesGeneratorStarter.generateRoutesAndAirportEntities(
                markets,
                ssimLines);
        final List<Flight> flights = flightGeneratorStarter.generateFlightsEntities(
                generatorConfiguration.getFlightConfiguration(),
                tariffs,
                routes);
        final List<Booking> bookings = bookingGeneratorStarter.generateBookingEntities(
                flights,
                generatorConfiguration.getCustomerConfiguration(),
                generatorConfiguration.getCoreBookingConfiguration(),
                generatorConfiguration.getServiceOrderConfiguration());
        coreBookingSerializer.generateFlattenData(bookings);

    }

    /**
     * Gets the available markets. I.e. all markets that appear in the list of
     * tariffs.
     * @param tariffs              generated tariffs
     * @return the available markets
     */
    private List<Market> getAvailableMarkets(final List<Tariff> tariffs){
        final HashSet<Market> marketSet = new HashSet<>();
        for (final Tariff tariff : tariffs) {
            marketSet.add(tariff.getMarket());
        }
        return new ArrayList<>(marketSet);
    }
}

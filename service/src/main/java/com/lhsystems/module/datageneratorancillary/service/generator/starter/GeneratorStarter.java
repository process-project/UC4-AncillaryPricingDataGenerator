package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Starts generating all entities in the proper order.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public class GeneratorStarter {

    /** Starts generating baggage entities. */
    private final BaggageGeneratorStarter baggageGeneratorStarter;

    /** Starts generating booking entities. */
    private final BookingGeneratorStarter bookingGeneratorStarter;

    /** Starts generating products entities. */
    private final ProductGeneratorStarter productGeneratorStarter;

    /** Starts generating seat entities. */
    private final SeatingGeneratorStarter seatingGeneratorStarter;

    /** Starts generating tariff entities. */
    private final TariffGeneratorStarter tariffGeneratorStarter;

    /** Starts generating flight entities. */
    private final FlightGeneratorStarter flightGeneratorStarter;

    /** Starts generating routes and airports. */
    private final RoutesGeneratorStarter routesGeneratorStarter;


    /**
     * Instantiates a new Generator starter.
     *
     * @param productGeneratorStarterParam
     *            the product generator starter
     * @param baggageGeneratorStarterParam
     *            the baggage generator starter
     * @param seatingGeneratorStarterParam
     *            the seating generator starter
     * @param tariffGeneratorStarterParam
     *            the tariff generator starter
     * @param flightGeneratorStarterParam
     *            the flight generator starter
     * @param routesGeneratorStarterParam
     *            the routes generator starter
     * @param bookingGeneratorStarterParam
     *            the booking generator starter
     */
    @Autowired
    public GeneratorStarter(final ProductGeneratorStarter productGeneratorStarterParam,
            final BaggageGeneratorStarter baggageGeneratorStarterParam,
            final SeatingGeneratorStarter seatingGeneratorStarterParam,
            final TariffGeneratorStarter tariffGeneratorStarterParam,
            final FlightGeneratorStarter flightGeneratorStarterParam,
            final RoutesGeneratorStarter routesGeneratorStarterParam,
            final BookingGeneratorStarter bookingGeneratorStarterParam) {
        productGeneratorStarter = productGeneratorStarterParam;
        baggageGeneratorStarter = baggageGeneratorStarterParam;
        seatingGeneratorStarter = seatingGeneratorStarterParam;
        tariffGeneratorStarter = tariffGeneratorStarterParam;
        flightGeneratorStarter = flightGeneratorStarterParam;
        routesGeneratorStarter = routesGeneratorStarterParam;
        bookingGeneratorStarter = bookingGeneratorStarterParam;
    }

    /**
     * Starts generating flight data in proper order.
     *
     * @param generatorConfiguration the generator configuration
     * @param ssimLines              the ssim lines
     */
    public final void generateData(
            final GeneratorConfiguration generatorConfiguration,
            final List<String> ssimLines,
            final List<Compartment> compartments) {
        final List<BaggageClass> baggageClasses = baggageGeneratorStarter.generateBaggageEntities(
                generatorConfiguration.getBaggageConfiguration());
        final List<Product> products = productGeneratorStarter.generateProductsEntities(
                baggageClasses,
                generatorConfiguration.getNumberProducts(),
                compartments);
        final List<SeatingModel> seatingModels = seatingGeneratorStarter.generateSeatingModel(
                generatorConfiguration.getSeatConfiguration());

        final List<Tariff> tariffs = tariffGeneratorStarter.generateTariffsEntities(
                products,
                seatingModels,
                generatorConfiguration.getNumberTariffs());

        final List<Market> markets = getAvailableMarkets(tariffs);

        final List<Route> routes = routesGeneratorStarter.generateRoutesAndAirports(markets, ssimLines);
        final List<Flight> flights = flightGeneratorStarter.generateFlightsEntities(
                generatorConfiguration.getFlightConfiguration(),
                tariffs,
                routes);
        final List<Booking> bookings = bookingGeneratorStarter.generateBookingEntities(
                generatorConfiguration.getNumberBookings(),
                flights);

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

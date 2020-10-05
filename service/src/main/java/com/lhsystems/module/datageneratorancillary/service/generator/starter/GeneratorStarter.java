package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.BatchBookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

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

    /** Starts generating products entities. */
    private final ProductGeneratorStarter productGeneratorStarter;

    /** Starts generating routes and airports. */
    private final RoutesGeneratorStarter routesGeneratorStarter;

    /** Starts generating seat entities. */
    private final SeatingGeneratorStarter seatingGeneratorStarter;

    /** Starts generating booking entities and save them in a batch */
    private final BatchBookingGenerator batchBookingGenerator;

    /**
     * Starts generating additional service entities besides seatGroups and
     * baggage.
     */
    private final ServiceGeneratorStarter serviceGeneratorStarter;

    /** Starts generating tariff entities. */
    private final TariffGeneratorStarter tariffGeneratorStarter;

    /**
     * Instantiates a new Generator starter.
     *            the flight generator starter
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
     * @param routesGeneratorStarterParam
     *            the routes generator starter
     * @param batchBookingGeneratorParam
     *            the booking generator
     */
    @Autowired
    public GeneratorStarter(final ProductGeneratorStarter productGeneratorStarterParam,
                            final BaggageGeneratorStarter baggageGeneratorStarterParam,
                            final SeatingGeneratorStarter seatingGeneratorStarterParam,
                            final ServiceGeneratorStarter serviceGeneratorStarterParam,
                            final TariffGeneratorStarter tariffGeneratorStarterParam,
                            final RoutesGeneratorStarter routesGeneratorStarterParam,
                            final BatchBookingGenerator batchBookingGeneratorParam) {
        productGeneratorStarter = productGeneratorStarterParam;
        baggageGeneratorStarter = baggageGeneratorStarterParam;
        seatingGeneratorStarter = seatingGeneratorStarterParam;
        serviceGeneratorStarter = serviceGeneratorStarterParam;
        tariffGeneratorStarter = tariffGeneratorStarterParam;
        routesGeneratorStarter = routesGeneratorStarterParam;
        batchBookingGenerator = batchBookingGeneratorParam;
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
    public void generateBasicData(
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

        batchBookingGenerator.generateBookingsInBatch(routes, tariffs, generatorConfiguration);
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

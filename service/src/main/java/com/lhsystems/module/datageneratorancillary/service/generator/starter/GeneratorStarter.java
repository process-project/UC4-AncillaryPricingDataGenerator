package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lhsystems.module.datageneratorancillary.service.data.*;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;

/**
 * Starts generating all entities in the proper order.
 *
 * @author MB
 * @version $Revision : 1.10 $
 */
@Service
public class GeneratorStarter {

    /** Starts generating products entities. */
    private final ProductGeneratorStarter productGeneratorStarter;

    /** Starts generating baggage entities. */
    private final BaggageGeneratorStarter baggageGeneratorStarter;

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
     * @param productGeneratorStarterParam the product generator starter param
     * @param baggageGeneratorStarterParam the baggage generator starter param
     * @param seatingGeneratorStarterParam the seating generator starter param
     * @param tariffGeneratorStarterParam  the tariff generator starter param
     * @param flightGeneratorStarterParam  the flight generator starter param
     * @param routesGeneratorStarterParam  the routes generator starter param
     */
    @Autowired
    public GeneratorStarter(final ProductGeneratorStarter productGeneratorStarterParam,
                            final BaggageGeneratorStarter baggageGeneratorStarterParam,
                            final SeatingGeneratorStarter seatingGeneratorStarterParam,
                            final TariffGeneratorStarter tariffGeneratorStarterParam,
                            final FlightGeneratorStarter flightGeneratorStarterParam,
                            final RoutesGeneratorStarter routesGeneratorStarterParam) {
        this.productGeneratorStarter = productGeneratorStarterParam;
        this.baggageGeneratorStarter = baggageGeneratorStarterParam;
        this.seatingGeneratorStarter = seatingGeneratorStarterParam;
        this.tariffGeneratorStarter = tariffGeneratorStarterParam;
        this.flightGeneratorStarter = flightGeneratorStarterParam;
        this.routesGeneratorStarter = routesGeneratorStarterParam;
    }

    /**
     * Starts generating flight data in proper order.
     *
     * @param maxId                  the max id
     * @param generatorConfiguration the generator configuration
     * @param ssimLines              the ssim lines
     */
    public final void generateData(final long maxId, final GeneratorConfiguration generatorConfiguration, final List<String> ssimLines) {
        final List<BaggageClass> baggageClasses = baggageGeneratorStarter.generateBaggageEntities(maxId, generatorConfiguration.getBaggage());
        final List<Product> products = productGeneratorStarter.generateProductsEntities(maxId, baggageClasses,
                generatorConfiguration.getProduct());

        final List<SeatingModel> seatingModels = seatingGeneratorStarter.generateSeatingModel(maxId, generatorConfiguration.getSeat());

        final List<Tariff> tariffs = tariffGeneratorStarter.generateTariffsEntities(maxId, products, seatingModels,
                generatorConfiguration.getTariff());

        final List<Market> markets = getAvailableMarkets(tariffs);

        final List<Route> routes = routesGeneratorStarter.generateRoutesAndAirports(markets, ssimLines);
        flightGeneratorStarter.generateFlightsEntities(maxId, generatorConfiguration.getFlight(), tariffs, routes);
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

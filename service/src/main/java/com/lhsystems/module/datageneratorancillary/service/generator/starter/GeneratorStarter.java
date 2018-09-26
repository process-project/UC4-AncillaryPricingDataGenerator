package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.*;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Starts generating all entities in the proper order
 *
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
public class GeneratorStarter {

    /** Starts generating products entities */
    private final ProductGeneratorStarter productGeneratorStarter;

    /** Starts generating baggage entities */
    private final BaggageGeneratorStarter baggageGeneratorStarter;

    /** Starts generating seat entities */
    private final SeatingGeneratorStarter seatingGeneratorStarter;

    /** Starts generating tariff entities */
    private final TariffGeneratorStarter tariffGeneratorStarter;

    /** Starts generating flight entities */
    private final FlightGeneratorStarter flightGeneratorStarter;

    /** Starts generating routes and airports */
    private final RoutesGeneratorStarter routesGeneratorStarter;


    @Autowired
    public GeneratorStarter(ProductGeneratorStarter productGeneratorStarter,
                            BaggageGeneratorStarter baggageGeneratorStarter,
                            SeatingGeneratorStarter seatingGeneratorStarter,
                            TariffGeneratorStarter tariffGeneratorStarter,
                            FlightGeneratorStarter flightGeneratorStarter,
                            RoutesGeneratorStarter routesGeneratorStarter) {
        this.productGeneratorStarter = productGeneratorStarter;
        this.baggageGeneratorStarter = baggageGeneratorStarter;
        this.seatingGeneratorStarter = seatingGeneratorStarter;
        this.tariffGeneratorStarter = tariffGeneratorStarter;
        this.flightGeneratorStarter = flightGeneratorStarter;
        this.routesGeneratorStarter = routesGeneratorStarter;
    }

    public void generateData(long maxId, GeneratorConfiguration generatorConfiguration, List<String> ssimLines) {
        List<BaggageClass> baggageClasses = baggageGeneratorStarter.generateBaggageEntities(maxId, generatorConfiguration.getBaggage());
        List<Product> products = productGeneratorStarter.generateProductsEntities(maxId, baggageClasses,
                generatorConfiguration.getProduct());

        List<SeatingModel> seatingModels = seatingGeneratorStarter.generateSeatingModel(maxId, generatorConfiguration.getSeat());

        List<Tariff> tariffs = tariffGeneratorStarter.generateTariffsEntities(maxId, products, seatingModels,
                generatorConfiguration.getTariff());

        List<Market> markets = getAvailableMarkets(tariffs);

        List<Route> routes = routesGeneratorStarter.generateRoutesAndAirports(markets, ssimLines);
        flightGeneratorStarter.generateFlightsEntities(maxId, generatorConfiguration.getFlight(), tariffs, routes);
    }

    private List<Market> getAvailableMarkets(List<Tariff> tariffs){
        final HashSet<Market> marketSet = new HashSet<>();
        for (final Tariff tariff : tariffs) {
            marketSet.add(tariff.getMarket());
        }
        return new ArrayList<>(marketSet);
    }
}

package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.SSIMFileReader;
import com.lhsystems.module.data.generator.ancillary.data.*;
import com.lhsystems.module.data.generator.ancillary.generator.configuration.GeneratorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GeneratorStarter {

    private final ProductGeneratorStarter productGeneratorStarter;
    private final BaggageGeneratorStarter baggageGeneratorStarter;
    private final SeatingGeneratorStarter seatingGeneratorStarter;
    private final TariffGeneratorStarter tariffGeneratorStarter;
    private final FlightGeneratorStarter flightGeneratorStarter;
    private final SSIMFileReader ssimFileReader;

    @Autowired
    public GeneratorStarter(ProductGeneratorStarter productGeneratorStarter,
                            BaggageGeneratorStarter baggageGeneratorStarter,
                            SeatingGeneratorStarter seatingGeneratorStarter,
                            TariffGeneratorStarter tariffGeneratorStarter,
                            FlightGeneratorStarter flightGeneratorStarter,
                            SSIMFileReader ssimFileReader) {
        this.productGeneratorStarter = productGeneratorStarter;
        this.baggageGeneratorStarter = baggageGeneratorStarter;
        this.seatingGeneratorStarter = seatingGeneratorStarter;
        this.tariffGeneratorStarter = tariffGeneratorStarter;
        this.flightGeneratorStarter = flightGeneratorStarter;
        this.ssimFileReader = ssimFileReader;
    }

    public void generateData(long maxId, GeneratorConfiguration generatorConfiguration) {
        List<BaggageClass> baggageClasses = baggageGeneratorStarter.generateBaggageEntities(maxId, generatorConfiguration.getBaggage());
        List<Product> products = productGeneratorStarter.generateProductsEntities(maxId, baggageClasses,
                generatorConfiguration.getProduct());

        List<SeatingModel> seatingModels = seatingGeneratorStarter.generateSeatingModel(maxId, generatorConfiguration.getSeat());

        List<Tariff> tariffs = tariffGeneratorStarter.generateTariffsEntities(maxId, products, seatingModels,
                generatorConfiguration.getTariff());

        List<Market> markets = getAvailableMarkets(tariffs);

        List<Route> routes = ssimFileReader.generateRoutesAndAirports(markets);
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

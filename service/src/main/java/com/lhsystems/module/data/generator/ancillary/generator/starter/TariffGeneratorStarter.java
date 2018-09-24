package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.data.Product;
import com.lhsystems.module.data.generator.ancillary.data.SeatingModel;
import com.lhsystems.module.data.generator.ancillary.data.Tariff;
import com.lhsystems.module.data.generator.ancillary.generator.core.TariffGenerator;
import com.lhsystems.module.data.generator.ancillary.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TariffGeneratorStarter {

    private final TariffRepository tariffRepository;

    @Autowired
    public TariffGeneratorStarter(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    List<Tariff> generateTariffsEntities(long startId, List<Product> products, List<SeatingModel> seatingModels, int tariffSize) {
        TariffGenerator tariffGenerator = new TariffGenerator(startId, products, seatingModels);
        final List<Tariff> tariffs = tariffGenerator.generateList(tariffSize);
        tariffRepository.saveAll(tariffs);
        return tariffs;
    }
}

package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.TariffConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.TariffGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.TariffRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Starts generating tariff entities and save them into database.
 *
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
class TariffGeneratorStarter {

    /** The repository used for saving tariffs. */
    private final TariffRepository tariffRepository;

    /**
     * Instantiates a new tariff generator starer with injected repositories.
     * @param tariffRepositoryParam
     *        repository responsible for crud operations on tariff entities
     */
    @Autowired
    public TariffGeneratorStarter(final TariffRepository tariffRepositoryParam) {
        tariffRepository = tariffRepositoryParam;
    }

    /**
     * Generate tariff entities in tariff generator and save them.
     *
     * @param products
     *            the products to be used for tariff generation
     * @param seatingModels
     *            the seating models to be used for tariff generation
     * @param tariffConfiguration
     *            the tariff configuration
     * @return the list of generated tariffs
     */
    List<Tariff> generateTariffsEntities(final List<Product> products,
            final List<SeatingModel> seatingModels,
            final TariffConfiguration tariffConfiguration) {
        final TariffGenerator tariffGenerator = new TariffGenerator(
                products,
                seatingModels,
                tariffConfiguration);
        final List<Tariff> tariffs = tariffGenerator.generateList(
                tariffConfiguration.getNumberTariff());
        tariffRepository.save(tariffs);
        return tariffs;
    }
}

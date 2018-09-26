package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.core.TariffGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * Instantiates a new tariff generator starer with injected repositories
     * @param tariffRepository
     *        repository responsible for crud operations on tariff entities
     */
    @Autowired
    public TariffGeneratorStarter(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    /**
     * Generate tariff entities in tariff generator and save them
     *
     * @param startId
     *        the smallest id used for data Generation
     * @param products
     *        the products to be used for tariff generation
     * @param seatingModels
     *        the seating models to be used for tariff generation
     * @param tariffSize
     *        the size of tariffs that should be generated
     * @return
     *        the list of generated tariffs
     */
    List<Tariff> generateTariffsEntities(long startId, List<Product> products, List<SeatingModel> seatingModels,
                                         int tariffSize) {
        TariffGenerator tariffGenerator = new TariffGenerator(startId, products, seatingModels);
        final List<Tariff> tariffs = tariffGenerator.generateList(tariffSize);
        tariffRepository.saveAll(tariffs);
        return tariffs;
    }
}

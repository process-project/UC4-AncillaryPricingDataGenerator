package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.data.BaggageClass;
import com.lhsystems.module.data.generator.ancillary.data.BaggageLimits;
import com.lhsystems.module.data.generator.ancillary.data.BaggagePricing;
import com.lhsystems.module.data.generator.ancillary.data.BaggageSize;
import com.lhsystems.module.data.generator.ancillary.generator.configuration.BaggageGeneratorConfiguration;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageClassGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageLimitsGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggagePricingGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageSizeGenerator;
import com.lhsystems.module.data.generator.ancillary.repository.BaggageClassRepository;
import com.lhsystems.module.data.generator.ancillary.repository.BaggageLimitsRepository;
import com.lhsystems.module.data.generator.ancillary.repository.BaggagePricingRepository;
import com.lhsystems.module.data.generator.ancillary.repository.BaggageSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Generates baggage entities and save them into database.
 *
 *
 * @author MB
 * @version $Revision: 1.10 $
 */

@Service
class BaggageGeneratorStarter {

    private final BaggageSizeRepository baggageSizeRepository;
    private final BaggageLimitsRepository baggageLimitsRepository;
    private final BaggagePricingRepository baggagePricingRepository;
    private final BaggageClassRepository baggageClassRepository;

    @Autowired
    public BaggageGeneratorStarter(BaggageSizeRepository baggageSizeRepository,
                                   BaggageLimitsRepository baggageLimitsRepository,
                                   BaggagePricingRepository baggagePricingRepository,
                                   BaggageClassRepository baggageClassRepository) {
        this.baggageSizeRepository = baggageSizeRepository;
        this.baggageLimitsRepository = baggageLimitsRepository;
        this.baggagePricingRepository = baggagePricingRepository;
        this.baggageClassRepository = baggageClassRepository;

    }

    List<BaggageClass> generateBaggageEntities(long startId, BaggageGeneratorConfiguration baggageConfiguration) {
        List<BaggageSize> baggageSizes = generateBaggageSize(startId, baggageConfiguration.getBaggageSize());
        List<BaggageLimits> baggageLimits = generateBaggageLimits(startId, baggageSizes, baggageConfiguration.getBaggageLimits());
        List<BaggagePricing> baggagePricingModels = generateBaggagePricings(startId, baggageConfiguration.getBaggagePricing());
        return generateBaggageClasses(startId, baggageLimits, baggagePricingModels, baggageConfiguration.getBaggageClass());
    }

    private List<BaggageSize> generateBaggageSize(long startId, int baggageSize){
        BaggageSizeGenerator baggageSizeGenerator = new BaggageSizeGenerator(startId);
        final List<BaggageSize> baggageSizes = baggageSizeGenerator.generateList(baggageSize);
        baggageSizeRepository.saveAll(baggageSizes);
        return baggageSizes;
    }

    private List<BaggageClass> generateBaggageClasses(long startId,
                                                      List<BaggageLimits> baggageLimits,
                                                      List<BaggagePricing> baggagePricingModels,
                                                      int baggageClassesSize) {
        BaggageClassGenerator baggageClassGenerator = new BaggageClassGenerator(startId, baggageLimits, baggagePricingModels);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(baggageClassesSize);
        baggageClassRepository.saveAll(baggageClasses);
        return baggageClasses;
    }

    private List<BaggagePricing> generateBaggagePricings(long startId, int pricingSize) {
        BaggagePricingGenerator baggagePricingGenerator = new BaggagePricingGenerator(startId);
        final List<BaggagePricing> baggagePricingModels =
                baggagePricingGenerator.generateList(pricingSize);
        baggagePricingRepository.saveAll(baggagePricingModels);
        return baggagePricingModels;
    }

    private List<BaggageLimits> generateBaggageLimits(long startId, List<BaggageSize> baggageSizes, int baggageLimitsSize) {
        BaggageLimitsGenerator baggageLimitsGenerator = new BaggageLimitsGenerator(startId, baggageSizes);
        final List<BaggageLimits> baggageLimits = baggageLimitsGenerator.generateList(baggageLimitsSize);
        baggageLimitsRepository.saveAll(baggageLimits);
        return baggageLimits;
    }


}

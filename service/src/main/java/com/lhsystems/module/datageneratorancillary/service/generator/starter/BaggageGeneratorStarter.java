package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageGeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BaggageClassGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BaggageLimitsGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BaggagePricingGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BaggageSizeGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggageClassRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggageLimitsRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggagePricingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.BaggageSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Starts generating baggage entities and save them into database.
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
class BaggageGeneratorStarter {

    /** The repository used for saving baggage size.*/
    private final BaggageSizeRepository baggageSizeRepository;

    /** The repository used for saving baggage limits.*/
    private final BaggageLimitsRepository baggageLimitsRepository;

    /** The repository used for saving baggage pricing.*/
    private final BaggagePricingRepository baggagePricingRepository;

    /** The repository used for saving baggage class.*/
    private final BaggageClassRepository baggageClassRepository;
    
    /**
     * Instantiates a new baggage generator starer with injected baggage repositories
     * @param baggageSizeRepository
     *        repository responsible for crud operations on flight entities
     * @param baggageLimitsRepository
     *        repository responsible for crud operations on baggage limits entities
     * @param baggagePricingRepository
     *        repository responsible for crud operations on baggage pricing entities
     * @param baggageClassRepository
     *        repository responsible for crud operations on baggage class entities
     */
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

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param baggageConfiguration
     *        the options used for baggage generator
     * @return
     *        the list of generated baggage classes
     */
    List<BaggageClass> generateBaggageEntities(long startId, BaggageGeneratorConfiguration baggageConfiguration) {
        List<BaggageSize> baggageSizes = generateBaggageSize(startId, baggageConfiguration.getBaggageSize());
        List<BaggageLimits> baggageLimits = generateBaggageLimits(startId, baggageSizes, baggageConfiguration.getBaggageLimits());
        List<BaggagePricing> baggagePricingModels = generateBaggagePricing(startId, baggageConfiguration.getBaggagePricing());
        return generateBaggageClasses(startId, baggageLimits, baggagePricingModels, baggageConfiguration.getBaggageClass());
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param baggageSize
     *        the number of baggage size that should be generated
     * @return
     *      the list of generated baggage sizes
     */
    private List<BaggageSize> generateBaggageSize(long startId, int baggageSize){
        BaggageSizeGenerator baggageSizeGenerator = new BaggageSizeGenerator(startId);
        final List<BaggageSize> baggageSizes = baggageSizeGenerator.generateList(baggageSize);
        baggageSizeRepository.saveAll(baggageSizes);
        return baggageSizes;
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param baggageLimits
     *        the baggage limits to be used for baggage class generation
     * @param baggagePricingModels
     *        the baggage pricing model to be used for baggage class generation
     * @param baggageClassesSize
     *        the size of baggage class that should be generated
     * @return
     *        the list of generated baggage classes
     */
    private List<BaggageClass> generateBaggageClasses(long startId,
                                                      List<BaggageLimits> baggageLimits,
                                                      List<BaggagePricing> baggagePricingModels,
                                                      int baggageClassesSize) {
        BaggageClassGenerator baggageClassGenerator =
                new BaggageClassGenerator(startId, baggageLimits, baggagePricingModels);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(baggageClassesSize);
        baggageClassRepository.saveAll(baggageClasses);
        return baggageClasses;
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param pricingSize
     *        the size of baggage pricing that should be generated
     * @return
     *        the list of generated baggage pricing
     */
    private List<BaggagePricing> generateBaggagePricing(long startId, int pricingSize) {
        BaggagePricingGenerator baggagePricingGenerator = new BaggagePricingGenerator(startId);
        final List<BaggagePricing> baggagePricingModels =
                baggagePricingGenerator.generateList(pricingSize);
        baggagePricingRepository.saveAll(baggagePricingModels);
        return baggagePricingModels;
    }

    /**
     * @param startId
     *        the smallest id used for data Generation
     * @param baggageSizes
     *        baggage sizes used for generation
     * @param baggageLimitsSize
     *        the size of baggage limits that should be generated
     * @return
     *        the list of generated baggage limits
     */
    private List<BaggageLimits> generateBaggageLimits(long startId, List<BaggageSize> baggageSizes,
                                                      int baggageLimitsSize) {
        BaggageLimitsGenerator baggageLimitsGenerator = new BaggageLimitsGenerator(startId, baggageSizes);
        final List<BaggageLimits> baggageLimits = baggageLimitsGenerator.generateList(baggageLimitsSize);
        baggageLimitsRepository.saveAll(baggageLimits);
        return baggageLimits;
    }


}

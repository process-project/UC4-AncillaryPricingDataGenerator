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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Instantiates a new baggage generator starer with injected baggage repositories.
     * @param baggageSizeRepositoryParam
     *        repository responsible for crud operations on flight entities
     * @param baggageLimitsRepositoryParam
     *        repository responsible for crud operations on baggage limits entities
     * @param baggagePricingRepositoryParam
     *        repository responsible for crud operations on baggage pricing entities
     * @param baggageClassRepositoryParam
     *        repository responsible for crud operations on baggage class entities
     */
    @Autowired
    public BaggageGeneratorStarter(final BaggageSizeRepository baggageSizeRepositoryParam,
            final BaggageLimitsRepository baggageLimitsRepositoryParam,
            final BaggagePricingRepository baggagePricingRepositoryParam,
            final BaggageClassRepository baggageClassRepositoryParam) {
        baggageSizeRepository = baggageSizeRepositoryParam;
        baggageLimitsRepository = baggageLimitsRepositoryParam;
        baggagePricingRepository = baggagePricingRepositoryParam;
        baggageClassRepository = baggageClassRepositoryParam;
    }

    /**
     * @param baggageConfiguration
     *        the options used for baggage generator
     * @return
     *        the list of generated baggage classes
     */
    List<BaggageClass> generateBaggageEntities(
            final BaggageGeneratorConfiguration baggageConfiguration) {
        final List<BaggageSize> baggageSizes = generateBaggageSize(
                baggageConfiguration.getBaggageSize());
        final List<BaggageLimits> baggageLimits = generateBaggageLimits(
                baggageSizes,
                baggageConfiguration.getBaggageLimits());
        final List<BaggagePricing> baggagePricingModels = generateBaggagePricing(
                baggageConfiguration.getBaggagePricing());
        return generateBaggageClasses(
                baggageLimits,
                baggagePricingModels,
                baggageConfiguration.getBaggageClass());
    }

    /**
     * @param baggageSize
     *        the number of baggage size that should be generated
     * @return
     *      the list of generated baggage sizes
     */
    private List<BaggageSize> generateBaggageSize(final int baggageSize) {
        final BaggageSizeGenerator baggageSizeGenerator = new BaggageSizeGenerator();
        final List<BaggageSize> baggageSizes = baggageSizeGenerator.generateList(baggageSize);
        baggageSizeRepository.save(baggageSizes);
        return baggageSizes;
    }

    /**
     *
     * @param baggageLimits
     *            the baggage limits to be used for baggage class generation
     * @param baggagePricingModels
     *            the baggage pricing model to be used for baggage class
     *            generation
     * @param baggageClassesSize
     *            the size of baggage class that should be generated
     * @return the list of generated baggage classes
     */
    private List<BaggageClass> generateBaggageClasses(
            final List<BaggageLimits> baggageLimits,
            final List<BaggagePricing> baggagePricingModels,
            final int baggageClassesSize) {
        final BaggageClassGenerator baggageClassGenerator =
                new BaggageClassGenerator(baggageLimits, baggagePricingModels);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(baggageClassesSize);
        baggageClassRepository.save(baggageClasses);
        return baggageClasses;
    }

    /**
     * @param pricingSize
     *        the size of baggage pricing that should be generated
     * @return
     *        the list of generated baggage pricing
     */
    private List<BaggagePricing> generateBaggagePricing(final int pricingSize) {
        final BaggagePricingGenerator baggagePricingGenerator = new BaggagePricingGenerator();
        final List<BaggagePricing> baggagePricingModels = baggagePricingGenerator.generateList(pricingSize);
        baggagePricingRepository.save(baggagePricingModels);
        return baggagePricingModels;
    }

    /**
     *
     * @param baggageSizes
     *            baggage sizes used for generation
     * @param baggageLimitsSize
     *            the size of baggage limits that should be generated
     * @return the list of generated baggage limits
     */
    private List<BaggageLimits> generateBaggageLimits(
            final List<BaggageSize> baggageSizes,
            final int baggageLimitsSize) {
        final BaggageLimitsGenerator baggageLimitsGenerator = new BaggageLimitsGenerator(
                baggageSizes);
        final List<BaggageLimits> baggageLimits = baggageLimitsGenerator.generateList(baggageLimitsSize);
        baggageLimitsRepository.save(baggageLimits);
        return baggageLimits;
    }


}

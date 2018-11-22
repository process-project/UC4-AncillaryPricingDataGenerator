package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageClassConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageLimitsConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggagePricingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BaggageSizeConfiguration;
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

/**
 * Starts generating baggage entities and save them into database.
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class BaggageGeneratorStarter {

    /** The repository used for saving baggage class.*/
    private final BaggageClassRepository baggageClassRepository;

    /** The repository used for saving baggage limits.*/
    private final BaggageLimitsRepository baggageLimitsRepository;

    /** The repository used for saving baggage pricing.*/
    private final BaggagePricingRepository baggagePricingRepository;

    /** The repository used for saving baggage size.*/
    private final BaggageSizeRepository baggageSizeRepository;

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
     * Generate entities of baggage sizes, baggage limits, baggage pricings and
     * baggage classes based on the given configurations.
     *
     * @param baggageClassConfiguration
     *            the baggage class configuration
     * @param baggageLimitsConfiguration
     *            the baggage limits configuration
     * @param baggagePricingConfiguration
     *            the baggage pricing configuration
     * @param baggageSizeConfiguration
     *            the baggage size configuration
     * @return the list of generated baggage classes
     */
    public List<BaggageClass> generateBaggageEntities(
            final BaggageClassConfiguration baggageClassConfiguration,
            final BaggageLimitsConfiguration baggageLimitsConfiguration,
            final BaggagePricingConfiguration baggagePricingConfiguration,
            final BaggageSizeConfiguration baggageSizeConfiguration) {
        final List<BaggageSize> baggageSizes = generateBaggageSize(
                baggageSizeConfiguration);
        final List<BaggageLimits> baggageLimits = generateBaggageLimits(
                baggageSizes,
                baggageLimitsConfiguration);
        final List<BaggagePricing> baggagePricingModels = generateBaggagePricing(
                baggagePricingConfiguration);
        return generateBaggageClasses(
                baggageLimits,
                baggagePricingModels,
                baggageClassConfiguration);
    }

    /**
     * Generate a list of baggage classes based on a given configuration.
     *
     * @param baggageLimits
     *            the baggage limits to be used for baggage class generation
     * @param baggagePricings
     *            the baggage pricing models to be used for baggage class
     *            generation
     * @param baggageClassConfiguration
     *            the baggage class configuration
     * @return the list of generated baggage classes
     */
    private List<BaggageClass> generateBaggageClasses(
            final List<BaggageLimits> baggageLimits,
            final List<BaggagePricing> baggagePricings,
            final BaggageClassConfiguration baggageClassConfiguration) {
        final BaggageClassGenerator baggageClassGenerator =
                new BaggageClassGenerator(baggageLimits, baggagePricings);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(
                baggageClassConfiguration.getNumberBaggageClass());
        baggageClassRepository.save(baggageClasses);
        return baggageClasses;
    }

    /**
     * Generate a list of baggage limits based on a given configuration.
     *
     * @param baggageSizes
     *            baggage sizes used for generation
     * @param baggageLimitsConfiguration
     *            the configuration of the generator
     * @return the list of generated baggage limits
     */
    private List<BaggageLimits> generateBaggageLimits(
            final List<BaggageSize> baggageSizes,
            final BaggageLimitsConfiguration baggageLimitsConfiguration) {
        final BaggageLimitsGenerator baggageLimitsGenerator = new BaggageLimitsGenerator(
                baggageSizes,
                baggageLimitsConfiguration);
        final List<BaggageLimits> baggageLimits = baggageLimitsGenerator.generateList(
                baggageLimitsConfiguration.getNumberBaggageLimits());
        baggageLimitsRepository.save(baggageLimits);
        return baggageLimits;
    }

    /**
     * Generate a list of baggage pricing based on a given configuration.
     *
     * @param baggagePricingConfiguration
     *            the configuration of the generator
     * @return the list of generated baggage pricing
     */
    private List<BaggagePricing> generateBaggagePricing(
            final BaggagePricingConfiguration baggagePricingConfiguration) {
        final BaggagePricingGenerator baggagePricingGenerator = new BaggagePricingGenerator(
                baggagePricingConfiguration);
        final List<BaggagePricing> baggagePricingModels = baggagePricingGenerator.generateList(
                baggagePricingConfiguration.getNumberBaggagePricing());
        baggagePricingRepository.save(baggagePricingModels);
        return baggagePricingModels;
    }

    /**
     * Generate a list of baggage sizes based on the given configuration.
     *
     * @param baggageSizeConfiguration
     *            the baggage size configuration
     * @return a list of generated baggage sizes
     */
    private List<BaggageSize> generateBaggageSize(
            final BaggageSizeConfiguration baggageSizeConfiguration) {
        final BaggageSizeGenerator baggageSizeGenerator = new BaggageSizeGenerator(
                baggageSizeConfiguration);
        final List<BaggageSize> baggageSizes = baggageSizeGenerator.generateList(
                baggageSizeConfiguration.getNumberBaggageSize());
        baggageSizeRepository.save(baggageSizes);
        return baggageSizes;
    }


}

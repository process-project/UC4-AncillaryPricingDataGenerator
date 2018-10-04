package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ProductConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates products randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ProductGenerator extends DataGenerator {

    /** The baggage classes to be used for product generation. */
    private final List<BaggageClass> baggageClasses;

    /** The compartments to be used product generation. */
    private final List<Compartment> compartments;

    /** The maximum number of baggage classes in a product. */
    private final int maxNumberBaggageClasses;

    /** The minimum number of baggage classes in a product. */
    private final int minNumberBaggageClasses;

    /**
     * Instantiates a new product generator.
     *
     * @param paramCompartments
     *            the compartments to be used for product generation
     * @param paramBaggageClasses
     *            the baggage classes to be used for product generation
     * @param productConfiguration
     *            the product configuration
     */
    public ProductGenerator(
            final List<Compartment> paramCompartments,
            final List<BaggageClass> paramBaggageClasses,
            final ProductConfiguration productConfiguration) {
        compartments = paramCompartments;
        baggageClasses = paramBaggageClasses;
        minNumberBaggageClasses = productConfiguration.getMinimumNumberBaggageClasses();
        maxNumberBaggageClasses = productConfiguration.getMaximumNumberBaggageClasses();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Product generate() {
        final Compartment compartment = getRandom().getOneRandomElement(compartments);
        final List<BaggageClass> chosenClasses = getRandom().getRandomlyManyElements(
                baggageClasses,
                minNumberBaggageClasses,
                maxNumberBaggageClasses);
        final StringBuilder nameBuilder = new StringBuilder();
        final Map<BaggageClass, Integer> numberOfIncludedBags = randomIncludedBags(chosenClasses);
        nameBuilder.append(compartment.getName()).append("_");
        for (final BaggageClass baggageClass : chosenClasses){
            nameBuilder.append(baggageClass.getName());
        }
        return new Product(
                nameBuilder.toString(),
                compartment,
                chosenClasses,
                numberOfIncludedBags);
    }


    /**
     * randomize how many bags of each class are included in tariffs of this
     * product.
     *
     * @param chosenBaggageClasses
     *            baggage classes for which we want to generate the number of
     *            included bags
     * @return the list
     */
    private Map<BaggageClass, Integer> randomIncludedBags(
            final List<BaggageClass> chosenBaggageClasses) {
        final HashMap<BaggageClass, Integer> includedBags = new HashMap<>();
        for (final BaggageClass baggageClass : baggageClasses){
            includedBags.put(
                    baggageClass,
                    getRandom().nextInt(
                            baggageClass.getBaggageLimits().getCountMax()));
        }
        return includedBags;
    }


}

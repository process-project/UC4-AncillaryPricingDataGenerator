package com.lhsystems.module.datageneratorancillary.service.generator;

import com.lhsystems.module.datageneratorancillary.service.ExtendedRandom;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;

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

    /** The maximum number of baggage classes in a product. */
    private static final int MAX_NUMBER_BAGGAGE_CLASSES = 4;

    /** The minimum number of baggage classes in a product. */
    private static final int MIN_NUMBER_BAGGAGE_CLASSES = 1;

    /** The baggage classes to be used for product generation. */
    private final List<BaggageClass> baggageClasses;

    /** The compartments to be used product generation. */
    private final List<Compartment> compartments;

    /**
     * Instantiates a new product generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     * @param paramRandom
     *            the random number generator
     * @param paramCompartments
     *            the compartments to be used for tariff generation
     * @param paramBaggageClasses
     *            the baggage classes to be used for product generation
     */
    public ProductGenerator(final Long startId,
            final ExtendedRandom paramRandom,
            final List<Compartment> paramCompartments,
            final List<BaggageClass> paramBaggageClasses) {
        super(startId, paramRandom);
        compartments = paramCompartments;
        baggageClasses = paramBaggageClasses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Product generate(final long id) {
        final Compartment compartment = getRandom().getOneRandomElement(compartments);
        final List<BaggageClass> chosenClasses = getRandom().getRandomlyManyElements(
                baggageClasses,
                MIN_NUMBER_BAGGAGE_CLASSES,
                MAX_NUMBER_BAGGAGE_CLASSES);
        final StringBuilder nameBuilder = new StringBuilder();
        final Map<BaggageClass, Integer> numberOfIncludedBags = randomIncludedBags(chosenClasses);
        nameBuilder.append(compartment.getName() + "_");
        for (final BaggageClass baggageClass : chosenClasses){
            nameBuilder.append(baggageClass.getName());
        }
        return new Product(
                id,
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

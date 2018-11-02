package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ProductConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates products randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ProductGenerator extends DataGenerator {

    /** The baggage classes to be used for product generation. */
    private final List<Service> services;

    /** The compartments to be used product generation. */
    private final List<Compartment> compartments;

    /** The maximum number of baggage classes in a product. */
    private final int maxNumberBaggageClasses;

    /** The minimum number of baggage classes in a product. */
    private final int minNumberBaggageClasses;

    /** The minimum number of seat groups offered in a product. */
    private final int minNumberSeatGroups;

    /** The maximum number of seat groups offered in a product. */
    private final int maxNumberSeatGroups;

    /**
     * Instantiates a new product generator.
     *
     * @param paramCompartments
     *            the compartments to be used for product generation
     * @param paramServices
     *            the baggage classes to be used for product generation
     * @param productConfiguration
     *            the product configuration
     */
    public ProductGenerator(
            final List<Compartment> paramCompartments,
            final List<Service> paramServices,
            final ProductConfiguration productConfiguration) {
        compartments = paramCompartments;
        services = paramServices;
        minNumberBaggageClasses = productConfiguration.getMinimumNumberBaggageClasses();
        maxNumberBaggageClasses = productConfiguration.getMaximumNumberBaggageClasses();
        minNumberSeatGroups = productConfiguration.getMinimumNumberSeatGroups();
        maxNumberSeatGroups = productConfiguration.getMaximumNumberSeatGroups();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Product generate() {
        final Compartment compartment = getRandom().getOneRandomElement(compartments);
        final List<Service> offeredServices = getRandom().getRandomlyManyElements(
                Service.getServicesByServiceClass(services, BaggageClass.class),
                minNumberBaggageClasses,
                maxNumberBaggageClasses);
        final StringBuilder nameBuilder = new StringBuilder();

        final Map<BaggageClass, Integer> numberOfIncludedBags = randomIncludedBags(
                Service.getServicesByServiceClass(
                        offeredServices,
                        BaggageClass.class).stream().map(
                                e -> (BaggageClass) e).collect(
                                        Collectors.toList()));
        nameBuilder.append(compartment.getName()).append("_");

        offeredServices.addAll(
                getRandom().getRandomlyManyElements(
                        Service.getServicesByServiceClass(
                                services,
                                SeatGroup.class),
                        minNumberSeatGroups,
                        maxNumberSeatGroups));
        for (final Service service : offeredServices) {
            nameBuilder.append(service.getName());
        }
        return new Product(
                nameBuilder.toString(),
                compartment,
                offeredServices,
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
        for (final BaggageClass baggageClass : chosenBaggageClasses) {
            includedBags.put(
                    baggageClass,
                    getRandom().nextInt(
                            baggageClass.getBaggageLimits().getCountMax()));
        }
        return includedBags;
    }


}

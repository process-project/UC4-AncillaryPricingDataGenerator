package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ProductConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.ProductGenerator;
import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

/**
 * Test <code>ProductGenerator</code>
 */
@RunWith(MockitoJUnitRunner.class)
public final class ProductGeneratorTest {

    /** The name of a product used for testing. */
    private static String PRODUCT_NAME = "compartment_bag";


    /** The baggage class. */
    private BaggageClass baggageClass;


    /** The compartment. */
    private Compartment compartment;

    /**
     * sets up the behavior of mocked objects. .
     */
    @Before
    public void setUp(){
        final BaggageSize baggageSize = new BaggageSize(3, 3, 3, 3);
        final BaggageLimits baggageLimits = new BaggageLimits(
                baggageSize,
                3,
                3);
        final BaggagePricing baggagePricing = new BaggagePricing(3, 3, 3);
        baggageClass = new BaggageClass(
                "bag",
                1,
                baggageLimits,
                baggagePricing);
        compartment = new Compartment('N', "compartment");
    }

    /**
     * Test generate list.
     */
    @Test
    public void testGenerateList() {
        final ExtendedRandom random = new ExtendedRandom();
        final List<Service> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final ProductConfiguration productConfiguration = new ProductConfiguration();
        productConfiguration.setMaximumNumberBaggageClasses(4);
        productConfiguration.setMinimumNumberBaggageClasses(1);
        final List<Compartment> compartments = new ArrayList<>();
        compartments.add(compartment);
        final ProductGenerator productGenerator = new ProductGenerator(
                compartments,
                baggageClasses,
                productConfiguration);
        final List<Product> testProducts = productGenerator.generateList(100);
        assertTrue(checkProducts(testProducts));
    }

    /**
     * Check products.
     *
     * @param testProducts
     *            the test products
     * @return true, if successful
     */
    private boolean checkProducts(final List<Product> testProducts) {
        for (final Product product: testProducts){
            for (final BaggageClass baggageClass : product.getServicesByClass(
                    BaggageClass.class).stream().map(
                            e -> (BaggageClass) e).collect(
                                    Collectors.toList())) {
                if (baggageClass.getBaggageLimits().getCountMax()< product.getNumberOfIncludedBagsByBaggageClass().get(baggageClass)){
                    return false;
                }
                if (!product.getName().equals(PRODUCT_NAME)) {
                    return false;
                }
            }

        }
        return true;
    }
}

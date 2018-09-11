package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.ExtendedRandom;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.generator.ProductGenerator;

import java.util.ArrayList;
import java.util.List;

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
                1,
                baggageSize,
                3,
                3);
        final BaggagePricing baggagePricing = new BaggagePricing(1, 3, 3, 3);
        baggageClass = new BaggageClass(
                1,
                "bag",
                baggageLimits,
                baggagePricing);
        compartment = new Compartment(1, 'N', "compartment");
    }

    /**
     * Test generate list.
     */
    @Test
    public void testGenerateList() {
        final ExtendedRandom random = new ExtendedRandom();
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final List<Compartment> compartments = new ArrayList<>();
        compartments.add(compartment);
        final ProductGenerator productGenerator = new ProductGenerator(
                (long) 10,
                random,
                compartments,
                baggageClasses);
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
            for (final BaggageClass baggageClass: product.getBaggageClasses()){
                if (baggageClass.getBaggageLimits().getCountMax()< product.getNumberOfIncludedBags().get(baggageClass)){
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
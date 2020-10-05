package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.TariffConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.TariffGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

/**
 * Test <code>ProductGenerator</code>
 */
@RunWith(MockitoJUnitRunner.class)
public final class TariffGeneratorTest {

    private Product product;

    @Before
    public void setUp() {
        final BaggageSize baggageSize = new BaggageSize(3, 3, 3, 3);
        final BaggageLimits baggageLimits = new BaggageLimits(
                baggageSize,
                3,
                3);
        final BaggagePricing baggagePricing = new BaggagePricing(3, 3, 3);
        final BaggageClass baggageClass = new BaggageClass(
                "baggageClass",
                1,
                baggageLimits,
                baggagePricing);
        final Compartment compartment = new Compartment('N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        final SeatGroup seatGroup = new SeatGroup("seatGroup", 1, 1);
        final List<Service> services = new ArrayList<>();
        services.add(seatGroup);
        services.addAll(baggageClasses);
        product = new Product(
                "product",
                compartment,
                services,
                includedBags);
        final ArrayList<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
    }

    /**
     * Test generate list.
     */
    @Test
    public void testGenerateList() {
        final List<Product> products = new ArrayList<>();
        products.add(product);
        final TariffConfiguration tariffConfiguration = new TariffConfiguration();
        tariffConfiguration.setMaximumPrice(200);
        tariffConfiguration.setMinimumPrice(50);
        final TariffGenerator tariffGenerator = new TariffGenerator(
                products,
                tariffConfiguration);
        final List<Tariff> testTariffs = tariffGenerator.generateList(100);
        assertTrue(checkTariffs(testTariffs));

    }

    private boolean checkTariffs(final List<Tariff> testTariffs) {
        for (final Tariff tariff : testTariffs) {
            if (!tariff.getProduct().equals(product)) {
                return false;
            }
        }
        return true;
    }

}

package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.generator.core.TariffGenerator;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;

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

    private SeatingModel seatingModel;

    @Before
    public void setUp() {
        final BaggageSize baggageSize = new BaggageSize(3, 3, 3, 3);
        final BaggageLimits baggageLimits = new BaggageLimits(
                1,
                baggageSize,
                3,
                3);
        final BaggagePricing baggagePricing = new BaggagePricing(1, 3, 3, 3);
        final BaggageClass baggageClass = new BaggageClass(
                1,
                "baggageClass",
                baggageLimits,
                baggagePricing);
        final Compartment compartment = new Compartment(1, 'N', "name");
        final List<BaggageClass> baggageClasses = new ArrayList<>();
        baggageClasses.add(baggageClass);
        final Map<BaggageClass, Integer> includedBags = new HashMap<>();
        includedBags.put(baggageClass, 1);
        product = new Product(
                1,
                "product",
                compartment,
                baggageClasses,
                includedBags);
        final SeatGroup seatGroup = new SeatGroup(1, "seatGroup", 1, 1);
        final ArrayList<SeatGroup> seatGroups = new ArrayList<>();
        seatGroups.add(seatGroup);
        seatingModel = new SeatingModel(3, seatGroups);
    }

    /**
     * Test generate list.
     */
    @Test
    public void testGenerateList() {
        final List<Product> products = new ArrayList<>();
        products.add(product);
        final List<SeatingModel> seatingModels = new ArrayList<>();
        seatingModels.add(seatingModel);
        final TariffGenerator tariffGenerator = new TariffGenerator(
                (long) 10,
                products,
                seatingModels);
        final List<Tariff> testTariffs = tariffGenerator.generateList(100);
        assertTrue(checkTariffs(testTariffs));

    }

    private boolean checkTariffs(final List<Tariff> testTariffs) {
        for (final Tariff tariff : testTariffs) {
            if (!tariff.getProduct().equals(product)) {
                return false;
            }
            if (!tariff.getSeating().equals(seatingModel)) {
                return false;
            }
        }
        return true;
    }

}

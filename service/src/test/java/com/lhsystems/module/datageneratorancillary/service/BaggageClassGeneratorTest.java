package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSize;
import com.lhsystems.module.datageneratorancillary.service.generator.core.BaggageClassGenerator;
import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * test <code>BaggageClassGenerator</code>.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(MockitoJUnitRunner.class)
public final class BaggageClassGeneratorTest {

    /**
     * Test generation of baggage classes, in particular test the generation of
     * the name of a baggage class.
     */
    @Test
    public void testGenerateBaggageClasses() {
        final List<BaggageLimits> limits = new ArrayList<>();
        final List<BaggagePricing> pricing = new ArrayList<>();
        limits.add(new BaggageLimits(new BaggageSize(1, 1, 1, 1), 1, 1));
        pricing.add(new BaggagePricing(1, 1, 1));
        final ExtendedRandom random = new ExtendedRandom();
        final BaggageClassGenerator baggageClassGenerator = new BaggageClassGenerator(
                limits,
                pricing);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(
                1);
        assertEquals("1.0kg_f1.0s1.0a1.0", baggageClasses.get(0).getName());
    }
}

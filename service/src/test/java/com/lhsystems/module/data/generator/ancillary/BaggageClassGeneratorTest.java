package com.lhsystems.module.data.generator.ancillary;

import com.lhsystems.module.data.generator.ancillary.data.BaggageClass;
import com.lhsystems.module.data.generator.ancillary.data.BaggageLimits;
import com.lhsystems.module.data.generator.ancillary.data.BaggagePricing;
import com.lhsystems.module.data.generator.ancillary.data.BaggageSize;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageClassGenerator;

import java.util.ArrayList;
import java.util.List;

import com.lhsystems.module.data.generator.ancillary.utils.ExtendedRandom;
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
        limits.add(new BaggageLimits(1, new BaggageSize(1,1,1,1), 1, 1));
        pricing.add(new BaggagePricing(1, 1, 1, 1));
        final ExtendedRandom random = new ExtendedRandom();
        final BaggageClassGenerator baggageClassGenerator = new BaggageClassGenerator(
                (long) 10,
                limits,
                pricing);
        final List<BaggageClass> baggageClasses = baggageClassGenerator.generateList(
                1);
        assertEquals("1.0kg_f1.0s1.0a1.0", baggageClasses.get(0).getName());
    }
}

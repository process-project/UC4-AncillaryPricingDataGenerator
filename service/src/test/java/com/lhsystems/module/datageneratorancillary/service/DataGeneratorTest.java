package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.ExtendedRandom;
import com.lhsystems.module.datageneratorancillary.service.generator.DataGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * test <code>DataGenerator</code>.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(MockitoJUnitRunner.class)
public class DataGeneratorTest {

    /**
     * Tests if <code>increaseIdCounter()</code> increases the counter correctly.
     */
    @Test
    public void testIncreaseIdCounter() {
        final ExtendedRandom random = new ExtendedRandom();
        final DataGenerator dataGenerator = new DataGenerator(
                (long) 3,
                random) {

            @Override
            protected Object generate(final long id) {
                return id;
            }
        };
        assertEquals((long) 3, dataGenerator.generateList(1).get(0));
        assertEquals((long) 4, dataGenerator.generateList(1).get(0));

    }
}

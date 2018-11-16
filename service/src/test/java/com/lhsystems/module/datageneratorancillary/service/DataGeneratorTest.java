package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.generator.core.DataGenerator;

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
        final DataGenerator dataGenerator = new DataGenerator(
                ) {

            @Override
            protected Object generate() {
                return (long) 1;
            }
        };
        assertEquals((long) 1, dataGenerator.generateList(1).get(0));
        assertEquals((long) 1, dataGenerator.generateList(1).get(0));

    }
}

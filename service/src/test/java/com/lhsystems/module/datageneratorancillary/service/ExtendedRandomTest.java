package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.text.MessageFormat;
import java.time.LocalDate;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the class ExtendedRandom.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(MockitoJUnitRunner.class)
public final class ExtendedRandomTest {

    /** The number of randomized elements to be tested. */
    private static final int SAMPLE_SIZE = 10;

    /** The random number Generator that is subject to tests. */
    private final ExtendedRandom random = new ExtendedRandom();

    /** The minimum date. */
    private final LocalDate minDate = LocalDate.of(2018, 1, 1);

    /** The maximum date date. */
    private final LocalDate maxDate = LocalDate.of(2018, 1, 11);

    /** The minimal int value. */
    private final int min = 10;

    /** The maximal int value. */
    private final int max = 20;

    /** The minimal double value. */
    private final double minDouble = 10;

    /** The maximal double value. */
    private final double maxDouble = 20;

    /**
     * Instantiates a new extended random test.
     */
    public ExtendedRandomTest() {

    }

    /**
     * Test GetCutOffGammaDistributedDouble. In particular, test if the random
     * numbers comply to the bounds set by <code>min</code> and <code>max</code>
     * on a sampleSize of <code>SAMPLE_SIZE</code>.
     */
    @Test
    public void testGetCutOffGammaDistributedDoubleBounds() {
        for (int counter = 0; counter < SAMPLE_SIZE; counter++) {
            for (int precision = -1; precision < 3; precision++) {
                final double someDouble = random.getCutOffGammaDistributedDouble(
                        min,
                        max,
                        precision,
                        1,
                        1);
                assertTrue(someDouble >= min);
                assertTrue(someDouble <= max);
            }
        }
    }

    /**
     * Test GetCutOffGammaDistributedDouble. In particular, test if the random
     * numbers comply to the bounds set by <code>min</code> and <code>max</code>
     * on a sampleSize of <code>SAMPLE_SIZE</code>.
     */
    @Test
    public void testGetCutOffGammaDistributedDoubleMinEqualsMax() {
        final double someDouble = random.getCutOffGammaDistributedDouble(
                3,
                3,
                1,
                1,
                1);
        assertEquals(3, someDouble, 0.1);
        try{
            random.getCutOffGammaDistributedDouble(3, 3, -1, 1, 1);
            fail("should have thrown exception");
        } catch (final Exception expectedException) {
        }
    }


    /**
     * Test getRandomDay. In particular, test if the random date complies to the
     * bounds set by <code>minDate</code> and <code>maxDate</code> on a
     * sampleSize of <code>SAMPLE_SIZE</code>.
     */
    @Test
    public void testGetRandomDay() {
        for (int counter = 0; counter < SAMPLE_SIZE; counter++) {
            final LocalDate someDate = random.getRandomDay(minDate, maxDate);
            assertTrue(someDate.compareTo(maxDate) <= 0);
            assertTrue(someDate.compareTo(minDate) >= 0);
        }
    }

    /**
     * Test nextInt(int,int). In particular, test if the random numbers comply
     * to the bounds set by <code>min</code> and <code>max</code> on a
     * sampleSize of <code>SAMPLE_SIZE</code>.
     *
     */
    @Test
    public void testNextInt() {
        for (int counter = 0; counter < SAMPLE_SIZE; counter++) {
            final int someInt = random.nextInt(min, max);
            assertTrue(someInt >= min);
            assertTrue(someInt < max);
        }
    }

    /**
     * Test getRandomRoundedDouble. In particular, test if the random numbers
     * complies to the bounds set by <code>minDate</code> and
     * <code>maxDate</code> and if they are rounded correctly.
     *
     */
    @Test
    public void testGetRandomRoundedDouble() {
        for (int exponent = 0; exponent <= 3; exponent++) {
            for (int counter = 0; counter < 60; counter++) {
                final double roundedDouble = random.getRandomRoundedDouble(
                        minDouble,
                        maxDouble,
                        exponent);
                final double multipliedResult =
                        roundedDouble
                        * (Math.pow(
                                10,
                                exponent));
                final double ceil = Math.ceil(multipliedResult);
                final double floor = Math.floor(multipliedResult);
                assertTrue(
                        MessageFormat.format(
                                "wrong rounding; floor {0}, ceil {1}, result {2}.",
                                floor, ceil,
                                roundedDouble),
                        Precision.equals(floor, multipliedResult, 0.1)
                        | Precision.equals(
                                ceil,
                                multipliedResult,
                                0.1));
                assertTrue(roundedDouble <= maxDouble);
                assertTrue(roundedDouble >= minDouble);
            }
        }
    }

}

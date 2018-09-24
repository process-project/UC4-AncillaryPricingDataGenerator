package com.lhsystems.module.data.generator.ancillary;

import com.lhsystems.module.data.generator.ancillary.data.Airport;

import com.lhsystems.module.data.generator.ancillary.data.Market;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the class Airport.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class AirportTest {

    /** The name of the test Airport. */
    public static final String TEST_AIRPORT_NAME = "Test Airport";

    /** The exception thrown by <code>setIataCode</code>. */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Instantiates a new airport test.
     */
    public AirportTest() {

    }

    /**
     * Test if an airport is considered equal to an instance of another class.
     */
    @Test
    public final void testEqualsDifferentClass() {
        final Airport airport1 = new Airport(
                "tes",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
        assertNotEquals(airport1, 1);
    }

    /**
     * Test if an airport is considered equal to an airport of value null.
     */
    @Test
    public final void testEqualsNull() {
        final Airport airport1 = new Airport(
                "tEs",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
        final Airport airport2 = null;
        assertNotEquals(airport1, airport2);
    }

    /**
     * Tests if an airport is considered equal to itself.
     */
    @Test
    public final void testEqualsTrivialTrue() {
        final Airport airport1 = new Airport(
                "tEs",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
        assertEquals(airport1, airport1);
    }

    /**
     * Tests if two airports with same member parameters are considered equal.
     */
    @Test
    public final void testEqualsTrue(){
        final Airport airport1 = new Airport("tes", TEST_AIRPORT_NAME, Market.DOMESTIC);
        final Airport airport2 = new Airport("TES", TEST_AIRPORT_NAME, Market.DOMESTIC);
        assertEquals(airport1, airport2);
    }

    /**
     * Test <code>setIataCode</code> by passing a correct 3 letter string.
     */
    @Test
    public final void testSetIata() {
        final Airport airport = new Airport(
                "Tes",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
        assertEquals(airport.getIata(), "TES");
    }

    /**
     * Test <code>setIataCode</code> by passing a four letter string.
     */
    @Test
    public final void testSetIataCodeFourLetters() {
        exception.expect(RuntimeException.class);
        new Airport(
                "TEST",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
    }

    /**
     * Test <code>setIataCode</code> by passing a string containing numbers.
     */
    @Test
    public final void testSetIataCodeNumbers() {
        exception.expect(RuntimeException.class);
        new Airport(
                "135",
                TEST_AIRPORT_NAME,
                Market.DOMESTIC);
    }
}

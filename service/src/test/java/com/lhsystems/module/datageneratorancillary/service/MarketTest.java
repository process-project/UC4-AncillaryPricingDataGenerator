package com.lhsystems.module.datageneratorancillary.service;

import java.util.Arrays;

import com.lhsystems.module.datageneratorancillary.service.data.Market;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

/**
 * Test Market.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@RunWith(Parameterized.class)
public class MarketTest {

    /** The first test input. */
    private final Market input1;

    /** The second test input. */
    private final Market input2;

    /** The return of <code>getMaximumMarket</code> expected. */
    private final Market expected;

    /**
     * Instantiates a new market test.
     *
     * @param paramInput1
     *            the first test input
     * @param paramInput2
     *            the second test input
     * @param paramExpected
     *            the expected return value
     */
    public MarketTest(final Market paramInput1, final Market paramInput2,
            final Market paramExpected) {
        input1 = paramInput1;
        input2 = paramInput2;
        expected = paramExpected;
    }


    /**
     * Test <code>getMaximumMarket()</code> for each combination of Markets.
     */
    @Test
    public final void testGetMaximumMarket() {
        assertEquals(expected, input1.getMaximumMarket(input2));
    }

    /**
     * Data to use for testing.
     *
     * @return the iterable
     */
    @Parameters(name = "{index}: getMaximumMarket({0},{1}) = {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]
                {
            { Market.DOMESTIC, Market.DOMESTIC, Market.DOMESTIC },
            { Market.DOMESTIC, Market.CONTINENTAL, Market.CONTINENTAL },
            { Market.DOMESTIC, Market.INTERCONTINENTAL,
                Market.INTERCONTINENTAL },
            { Market.CONTINENTAL, Market.DOMESTIC, Market.CONTINENTAL },
            { Market.CONTINENTAL, Market.CONTINENTAL,
                Market.CONTINENTAL },
            { Market.CONTINENTAL, Market.INTERCONTINENTAL,
                    Market.INTERCONTINENTAL },
            { Market.INTERCONTINENTAL, Market.DOMESTIC,
                        Market.INTERCONTINENTAL },
            { Market.INTERCONTINENTAL, Market.CONTINENTAL,
                            Market.INTERCONTINENTAL },
            { Market.INTERCONTINENTAL, Market.INTERCONTINENTAL,
                                Market.INTERCONTINENTAL } });
    }

}

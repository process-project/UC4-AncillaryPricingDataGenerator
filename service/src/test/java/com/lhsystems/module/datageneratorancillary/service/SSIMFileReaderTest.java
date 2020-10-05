package com.lhsystems.module.datageneratorancillary.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitx.framework.ListAssert;

/**
 * Tests the class SSIMFileReaderTest.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@RunWith(value = JUnitParamsRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class SSIMFileReaderTest {

    /** Object used for ensure that spring will run without using SpringRunner */
    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    /** Object used for ensure that spring will run without using SpringRunner */
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    /** Ssim file reader class to test. */
    private final SSIMFileReader ssimFileReader = new SSIMFileReader();

    /**
     * Test that ssim file is read in proper way and filter wrong lines.
     */
    @Test
    public void getSsimFileLines() {
        final String testFileName = "/ssim-test.ssim";
        final String firstLine = "3 LH 4321xxxxJ01APR1402APR141234567 KRK15001500+0100  FRA17001700+0100  M1F                                                     YF                  L                       PP26                  000003";
        final String secondLine = "3 LH  631xxxxJ01APR1401APR141234567 FRA19001900+0100  TXL20002000+0100  M1F                                                     YF                  L                       PP26                  000003";
        final List<String> expectedResult = new ArrayList<>();
        expectedResult.add(firstLine);
        expectedResult.add(secondLine);

        final List<String> ssimFileLines = ssimFileReader.getSsimFileLines(testFileName);

        ListAssert.assertEquals(expectedResult, ssimFileLines);
    }

    /**
     * Test if line is started with proper number, tested using parameters generating in method startedLineData.
     *
     * @param line      current line to check
     * @param result    expected result for this line
     */
    @Test
    @Parameters(method = "startedLineData")
    public void isLineStartedWithProperNumber(final String line, final boolean result)  {
        assertEquals(result, ssimFileReader.isLineStartedWithProperNumber(line));
    }

    /**
     * Method used for generating parameters tests.
     * @return array of objects used to test method
     */
    private Object[] startedLineData() {
        return new Object[]{
                new Object[]{"", true},
                new Object[]{"122222222", false},
                new Object[]{"0213", false},
                new Object[]{"4313", false},
                new Object[]{"aaaa", true},
                new Object[]{"3213", true}
        };
    }
}

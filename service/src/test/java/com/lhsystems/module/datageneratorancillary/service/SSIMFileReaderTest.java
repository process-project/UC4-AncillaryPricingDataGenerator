/*
package com.lhsystems.module.datageneratorancillary.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;


import static org.junit.Assert.*;

@RunWith(value = JUnitParamsRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)

public class SSIMFileReaderTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


    @Autowired
    private SSIMFileReader ssimFileReader;


    @Test
    @Parameters(method = "startedLineData")
    public void isLineStartedWithProperNumber(String line, boolean result) throws Exception {
        assertEquals(result, ssimFileReader.isLineStartedWithProperNumber(line));
    }

    private Object[] startedLineData() {
        return new Object[]{
                new Object[]{"", true},
                new Object[]{"122222222", false},
                new Object[]{"0213", false},
                new Object[]{"aaaa", true},
                new Object[]{"3213", true}
        };
    }
}*/

package com.lhsystems.module.datageneratorancillary.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Reads options for data generators, generates a number of data objects and especially flights and stores them in a sqlite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class AncillaryGeneratorMain {

    /**
     * Instantiates a new ancillary generator main. Default constructor to
     * satisfy checkstyle requirements.
     */
    private AncillaryGeneratorMain() {

    }

    /**
     * The main method. Starts up the main bean.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        final AbstractApplicationContext context = new AnnotationConfigApplicationContext(SqlContextConfiguration.class);
        final MainBean bean = context.getBean(MainBean.class);
        bean.start(args);
        context.close();
    }

}

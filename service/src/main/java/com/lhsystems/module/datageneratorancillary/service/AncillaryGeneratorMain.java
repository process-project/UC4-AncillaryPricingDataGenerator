package com.lhsystems.module.datageneratorancillary.service;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Reads options for data generators, generates a number of data objects and especially flights and stores them in a sqlite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class AncillaryGeneratorMain {

    public static void main(final String[] args) throws SQLException, ClassNotFoundException {
        final AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final MainBean bean = context.getBean(MainBean.class);
        bean.start(args);
    }

}

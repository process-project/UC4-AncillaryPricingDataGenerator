package com.lhsystems.module.data.generator.ancillary;

import com.lhsystems.module.data.generator.ancillary.sqlite.SqliteConnection;
import com.lhsystems.module.data.generator.ancillary.sqlite.read.SqliteReader;
import com.lhsystems.module.data.generator.ancillary.sqlite.write.SqliteWriter;
import com.lhsystems.module.data.generator.ancillary.data.BaggageClass;
import com.lhsystems.module.data.generator.ancillary.data.BaggageLimits;
import com.lhsystems.module.data.generator.ancillary.data.BaggagePricing;
import com.lhsystems.module.data.generator.ancillary.data.Flight;
import com.lhsystems.module.data.generator.ancillary.data.Product;
import com.lhsystems.module.data.generator.ancillary.data.SeatGroup;
import com.lhsystems.module.data.generator.ancillary.data.SeatingModel;
import com.lhsystems.module.data.generator.ancillary.data.Tariff;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageClassGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggageLimitsGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.BaggagePricingGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.DataGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.FlightGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.ProductGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.SeatGroupGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.SeatingModelGenerator;
import com.lhsystems.module.data.generator.ancillary.generator.core.TariffGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.lhsystems.module.data.generator.ancillary.utils.ExtendedRandom;
import com.lhsystems.module.data.generator.ancillary.utils.OptionFileKeys;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final MainBean bean = context.getBean(MainBean.class);
        bean.start(args);
    }

}

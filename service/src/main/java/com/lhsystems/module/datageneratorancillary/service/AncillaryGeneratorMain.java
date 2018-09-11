package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageLimits;
import com.lhsystems.module.datageneratorancillary.service.data.BaggagePricing;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.BaggageClassGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.BaggageLimitsGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.BaggagePricingGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.DataGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.FlightGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.ProductGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.SeatGroupGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.SeatingModelGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.TariffGenerator;
import com.lhsystems.module.datageneratorancillary.service.read.sqlite.SqliteConnection;
import com.lhsystems.module.datageneratorancillary.service.read.sqlite.SqliteReader;
import com.lhsystems.module.datageneratorancillary.service.write.sqlite.SqliteWriter;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Reads options for data generators, generates a number of data objects and especially flights and stores them in a sqlite database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class AncillaryGeneratorMain {


    /**
     * The Path to be used if the path option is not given.
     */
    private static String DEFAULT_OPTIONS_FILE = "/options.yml";

    /**
     * Name of the option giving the yaml File containing the options.
     */
    private static String OPTION_NAME_OPTIONS_FILE = "optionsFile";

    /** The generator used for generating baggage classes. */
    private static BaggageClassGenerator baggageClassGenerator;

    /** The generator used for generating baggage limits. */
    private static BaggageLimitsGenerator baggageLimitsGenerator;

    /** The generator used for generating baggage pricin models. */
    private static BaggagePricingGenerator baggagePricingGenerator;

    /**
     * Generates a list of Flights.
     */
    private static FlightGenerator flightGenerator;

    /** The generator used for generating products. */
    private static ProductGenerator productGenerator;

    /**
     * Used to read a Yaml Options file and get the options.
     */
    private static YamlOptionReader optionReader;

    /**
     * The random number generator used for all randomized generation processes.
     */
    private static ExtendedRandom random = new ExtendedRandom();

    /** The generator used for generating seat groups. */
    private static SeatGroupGenerator seatGroupGenerator;

    /** The generator used for generating seating models. */
    private static SeatingModelGenerator seatingModelGenerator;

    /**
     * Reads data from a SQLite database.
     */
    private static SqliteReader sqliteReader;

    /**
     * Used to write the generated flights into the database.
     */
    private static SqliteWriter sqliteWriter;

    /** The generator used for generating tariffs. */
    private static TariffGenerator tariffGenerator;

    /**
     * Instantiates a new flight generator main.
     */
    private AncillaryGeneratorMain() {
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * SQLite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object. Afterwards saves the
     * generated flights in a SQLite dataBase.
     *
     * @param args
     *            default parameter for main method. No use for now.
     * @throws SQLException
     *             if something went wrong during communication to the SQLite
     *             database
     * @throws ClassNotFoundException
     *             if the class "org.sqlite.JDBC" can't found.
     * @throws ParseException
     *             the parse exception
     */
    // CHECKSTYLE:OFF
    public static void main(final String[] args)
            throws SQLException, ClassNotFoundException, ParseException {
        // CHECKSTYLE:ON
        initializeReaderAndWriter(args);
        final long maxId = sqliteReader.getMaxId() + 1;
        generateAirlines(maxId);
        final List<Flight> flights = flightGenerator.generateList(
                optionReader.getNumberOf(OptionFileKeys.FLIGHT_NAME));
        sqliteWriter.writeList(flights, OptionFileKeys.FLIGHT_NAME);
        sqliteWriter.writeTariffsOfFlights(flights, maxId);
    }

    /**
     * Generate array list of type type.
     *
     * @param <T>
     *            the generic of elements in the ArrayList
     * @param generator
     *            the generator to be used for generation
     * @param numberOfElements
     *            the number of elements to be generated
     * @return an array list containing T objects
     */
    private static <T> List<T> generateArrayList(
            final DataGenerator generator, final int numberOfElements) {
        return generator.generateList(
                numberOfElements).stream().map(
                        e -> (T) e).collect(
                                Collectors.toList());
    }

    /**
     * Reads the command line option FILE_OPTIONS_NAME and returns its value.
     *
     * @param args
     *            default parameter for parsing, no use for now
     * @return value of command line option FILE_OPTIONS_NAME
     * @throws ParseException
     *             if command line cant be parsed
     */
    private static String getYamlPath(final String[] args)
            throws ParseException {
        final Options cmdOptions = new Options();
        cmdOptions.addOption(
                OPTION_NAME_OPTIONS_FILE,
                true,
                "Path of the yaml options file. Defaults to" + DEFAULT_OPTIONS_FILE);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine line = parser.parse(cmdOptions, args);
        if (line.hasOption(OPTION_NAME_OPTIONS_FILE)) {
            return line.getOptionValue(OPTION_NAME_OPTIONS_FILE);
        } else {
            return DEFAULT_OPTIONS_FILE;
        }
    }

    /**
     * Initialize ancillary generators and generate the necessary data for this.
     *
     * @param startId
     *            the start id to be used in the generators
     * @throws SQLException
     *             when writing data into SQLite database
     */
    private static void generateAncillaries(final long startId)
            throws SQLException {
        baggageLimitsGenerator = new BaggageLimitsGenerator(startId, random);
        baggagePricingGenerator = new BaggagePricingGenerator(startId, random);
        seatGroupGenerator = new SeatGroupGenerator(
                startId,
                random);
        final List<BaggageLimits> baggageLimits = generateArrayList(
                baggageLimitsGenerator,
                optionReader.getNumberOf(OptionFileKeys.BAGGAGE_LIMITS_NAME));
        sqliteWriter.writeList(
                baggageLimits,
                OptionFileKeys.BAGGAGE_LIMITS_NAME);
        final List<BaggagePricing> baggagePricingModels = generateArrayList(
                baggagePricingGenerator,
                optionReader.getNumberOf(OptionFileKeys.BAGGAGE_PRICING_NAME));
        sqliteWriter.writeList(
                baggagePricingModels,
                OptionFileKeys.BAGGAGE_PRICING_NAME);
        baggageClassGenerator = new BaggageClassGenerator(
                startId,
                random,
                baggageLimits,
                baggagePricingModels);
        final List<SeatGroup> seatGroups = generateArrayList(
                seatGroupGenerator,
                optionReader.getNumberOf(OptionFileKeys.SEAT_GROUP_NAME));
        sqliteWriter.writeList(seatGroups, OptionFileKeys.SEAT_GROUP_NAME);
        seatingModelGenerator = new SeatingModelGenerator(
                startId,
                random,
                seatGroups);
    }

    /**
     * Create all the generators and read and generate data necessary for this.
     *
     * @param maxId
     *            an upper bound for ids in the tables
     *
     * @throws SQLException
     *             the SQLException from <code>getCompartments()</code>
     */
    private static void generateAirlines(final long maxId)
            throws SQLException {
        generateAncillaries(maxId);
        final List<BaggageClass> baggageClasses = generateArrayList(
                baggageClassGenerator,
                optionReader.getNumberOf(OptionFileKeys.BAGGAGE_CLASS_NAME));
        sqliteWriter.writeList(
                baggageClasses,
                OptionFileKeys.BAGGAGE_CLASS_NAME);
        final List<SeatingModel> seatingModels = generateArrayList(
                seatingModelGenerator,
                optionReader.getNumberOf(OptionFileKeys.SEATING_MODEL_NAME));
        sqliteWriter.writeList(
                seatingModels,
                OptionFileKeys.SEATING_MODEL_NAME);
        sqliteWriter.writeSeatGroupsOfSeatingModels(seatingModels, maxId);
        productGenerator = new ProductGenerator(
                maxId,
                random,
                sqliteReader.getCompartments(),
                baggageClasses);
        final List<Product> products = generateArrayList(
                productGenerator,
                optionReader.getNumberOf(OptionFileKeys.PRODUCT_NAME));
        sqliteWriter.writeList(products, OptionFileKeys.PRODUCT_NAME);
        sqliteWriter.writeBaggageClassesOfProducts(products, maxId);
        tariffGenerator = new TariffGenerator(
                maxId,
                random,
                products,
                seatingModels);
        final List<Tariff> tariffs = generateArrayList(
                tariffGenerator,
                optionReader.getNumberOf(OptionFileKeys.TARIFF_NAME));
        sqliteWriter.writeList(tariffs, OptionFileKeys.TARIFF_NAME);
        flightGenerator = new FlightGenerator(
                maxId,
                random,
                sqliteReader.getAirports(),
                tariffs,
                optionReader.getMinDate(),
                optionReader.getMaxDate());
    }

    /**
     * Initialize readers and writers necessary for communication with the
     * database as well as reading from the option file.
     *
     * @param args
     *            the args
     * @throws ParseException
     *             the parse exception
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws SQLException
     *             the SQL exception
     */
    private static void initializeReaderAndWriter(final String[] args)
            throws ParseException, ClassNotFoundException, SQLException {
        final String yamlPath = getYamlPath(args);
        optionReader = new YamlOptionReader(yamlPath);
        final SqliteConnection connection = new SqliteConnection(
                optionReader.getDatabasePath());
        sqliteWriter = new SqliteWriter(connection.getConnection());
        sqliteReader = new SqliteReader(connection.getConnection());
    }

}

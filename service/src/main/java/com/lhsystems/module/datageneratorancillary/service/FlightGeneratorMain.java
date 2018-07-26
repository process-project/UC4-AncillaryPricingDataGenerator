package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.read.sqlite.SqliteConnection;
import com.lhsystems.module.datageneratorancillary.service.read.sqlite.SqliteReader;
import com.lhsystems.module.datageneratorancillary.service.write.sqlite.SqliteWriter;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Reads options for <code>FlightGenerator</code>, generates a number of flights
 * and stores them in a sqlite database.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */
final public class FlightGeneratorMain {

    /**
     * Name of the option giving the yaml File containing the options
     */
    private static String OPTION_NAME_OPTIONS_FILE = "optionsFile";

    /**
     * The Path to be used if the path option is not given.
     */
    private static String DEFAULT_OPTIONS_FILE = "/options.yml";

    /**
     * Used to read a Yaml Options file and get the options.
     */
    private static YamlOptionReader optionReader;

    /**
     * Used to write the generated flights into the database.
     */
    private static SqliteWriter sqliteExporter;

    /**
     * Generates a list of Flights.
     */
    private static FlightGenerator flightGenerator;

    /**
     * Reads data from a SQLite database.
     */
    private static SqliteReader sqliteReader;

    public FlightGeneratorMain() {
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
     * @throws ClassNotFoundException
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(final String[] args) throws SQLException,
    ClassNotFoundException, org.apache.commons.cli.ParseException {
        final String yamlPath = getYamlPath(args);
        optionReader = new YamlOptionReader(yamlPath);
        final SqliteConnection connection = new SqliteConnection(
                optionReader.getDbPath());
        sqliteExporter = new SqliteWriter(connection.getConnection());
        sqliteReader = new SqliteReader(connection.getConnection());
        flightGenerator = new FlightGenerator(
                sqliteReader.getMaxId() + 1,
                sqliteReader.getAirports());
        final ArrayList<Flight> flights = (ArrayList<Flight>) flightGenerator.generateFlights(
                optionReader.getNumberFLights());
        sqliteExporter.write(flights, sqliteReader.getMarkets());
    }

    /**
     * Reads the command line option FILE_OPTIONS_NAME and returns its value.
     *
     * @param args
     * @return value of command line option FILE_OPTIONS_NAME
     * @throws org.apache.commons.cli.ParseException
     */
    private static String getYamlPath(final String[] args)
            throws org.apache.commons.cli.ParseException

    {
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


}

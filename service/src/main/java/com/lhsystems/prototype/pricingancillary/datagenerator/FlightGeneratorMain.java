package com.lhsystems.prototype.pricingancillary.datagenerator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * reads options for <code>FlightGenerator</code>, generates a number of flights
 * and stores them in a sqlite database
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */
final public class FlightGeneratorMain {

    /**
     * used to read a Yaml Options file and get the options
     */
    private static YamlOptionReader optionReader;

    /**
     * Used to read the database and write the generated flights into it.
     */
    private static SqliteExporter sqliteExporter;

    /**
     * Generates a list of Flights.
     */
    private static FlightGenerator flightGenerator;


    public FlightGeneratorMain() {
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * sqlite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object. Afterwards saves the
     * generated flights in a sqlite dataBase.
     *
     * @param args
     *            default parameter for main method. No use for now.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(final String[] args)
            throws SQLException, ClassNotFoundException {
        optionReader = new YamlOptionReader("/options.yml");
        sqliteExporter = new SqliteExporter(optionReader.getDbPath());
        final Long startId = sqliteExporter.readMaxId() + 1;

        flightGenerator = new FlightGenerator(startId);

        final ArrayList<Flight> flights = (ArrayList<Flight>) flightGenerator.generateFlights(
                optionReader.getNumberFLights());
        sqliteExporter.exportToSqlite(flights);
    }

}


package com.lhsystems.module.datageneratorancillary.service;

import java.io.InputStream;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

/**
 * Reads a Yaml Options file and returns the options.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public class YamlOptionReader {

    /**
     * Specifies the keys of database options in the yaml file.
     */
    private static String SECTION_FOR_DATABASE_OPTIONS = "databaseOptions";

    /**
     * Specifies the keys of generator options in the yaml file.
     */
    private static String SECTION_FOR_GENERATOR_OPTIONS = "generatorOptions";

    /**
     * Specifies the key of the database option that states where to find the
     * database.
     */
    private static String SUBSECTION_DATABASE_PATH = "dbPath";

    /**
     * Specifies the key of the generator option that states how many flights to
     * generate.
     */
    private static String SUBSECTION_NUMBER_OF_FLIGHTS = "numberFlights";

    /**
     * Maps Keys to their respective options. Contains informations for the data
     * generator
     */
    private final HashMap<String, Object> options;

    /**
     * Constructor. Reads a Yaml file of the form of options.yml.template.
     *
     * @param optionsPath
     *            Path of the Yaml file containing the options
     */
    @SuppressWarnings("unchecked")
    public YamlOptionReader(final String optionsPath) {
        final InputStream input = FlightGeneratorMain.class.getResourceAsStream(
                optionsPath);
        final Yaml yaml = new Yaml();
        options = (HashMap<String, Object>) yaml.load(input);
    }

    /**
     * Returns the path of the database as stated in the loaded file.
     *
     * @return the path of the database as stated in the loaded file
     */
    @SuppressWarnings("unchecked")
    public final String getDbPath() {
        return (String) ((HashMap<String, Object>) options.get(
                SECTION_FOR_DATABASE_OPTIONS)).get(SUBSECTION_DATABASE_PATH);
    }

    /**
     * Returns the number of flights as stated in the loaded file.
     *
     * @return the number of flights as stated in the loaded file
     */
    @SuppressWarnings("unchecked")
    public final int getNumberFLights() {
        return (int) ((HashMap<String, Object>) options.get(
                SECTION_FOR_GENERATOR_OPTIONS)).get(
                        SUBSECTION_NUMBER_OF_FLIGHTS);
    }
}

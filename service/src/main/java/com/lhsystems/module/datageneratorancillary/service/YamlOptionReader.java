package com.lhsystems.module.datageneratorancillary.service;

import java.io.InputStream;
import java.time.LocalDate;
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
     * Specifies the key of the generator option that states the minimal flight
     * Date.
     */
    static final String SUBSECTION_MIN_DATE = "minFlightDate";

    /**
     * Specifies the key of the generator option that states the maximal flight
     * Date.
     */
    static final String SUBSECTION_MAX_DATE = "maxFlightDate";

    /**
     * Specifies the keys of database options in the yaml file.
     */
    static final String SECTION_FOR_DATABASE_OPTIONS = "databaseOptions";

    /**
     * Specifies the keys of generator options in the yaml file.
     */
    static final String SECTION_FOR_GENERATOR_OPTIONS = "generatorOptions";

    /**
     * Specifies the key of the database option that states where to find the
     * database.
     */
    static final String SUBSECTION_DATABASE_PATH = "dbPath";

    /**
     * Specifies the key of the generator option that states how many flights to
     * generate.
     */
    static final String SUBSECTION_NUMBER_OF_FLIGHTS = "numberFlights";

    /**
     * Maps Keys to their respective options. Contains informations for the data
     * generator
     */
    private HashMap<String, Object> options;

    /**
     * Constructor. Reads a Yaml file of the form of options.yml.template.
     *
     * @param optionsPath
     *            Path of the Yaml file containing the options
     */
    @SuppressWarnings("unchecked")
    public YamlOptionReader(final String optionsPath) {
        final InputStream input = getClass().getResourceAsStream(
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
    public final int getNumberFlights() {
        int result = 0;
        if (options.keySet().contains(SECTION_FOR_GENERATOR_OPTIONS)) {
            if (((HashMap<String, Object>) options.get(
                    SECTION_FOR_GENERATOR_OPTIONS)).keySet().contains(
                            SUBSECTION_NUMBER_OF_FLIGHTS)) {
                result = (int) ((HashMap<String, Object>) options.get(
                        SECTION_FOR_GENERATOR_OPTIONS)).get(
                                SUBSECTION_NUMBER_OF_FLIGHTS);
            }
        }
        return result;
    }

    /**
     * Gets the min date from the loaded file.
     *
     * @return the min date
     */
    @SuppressWarnings("unchecked")
    public final LocalDate getMinDate() {
        LocalDate result = null;
        if (options.keySet().contains(SECTION_FOR_GENERATOR_OPTIONS)) {
            if (((HashMap<String, Object>) options.get(
                    SECTION_FOR_GENERATOR_OPTIONS)).keySet().contains(
                            SUBSECTION_MIN_DATE)) {
                result = LocalDate.parse((String) ((HashMap<String, Object>) options.get(
                        SECTION_FOR_GENERATOR_OPTIONS)).get(
                                SUBSECTION_MIN_DATE));
            }
        }
        return result;
    }

    /**
     * Gets the max date from the loaded file.
     *
     * @return the max date
     */
    @SuppressWarnings("unchecked")
    public final LocalDate getMaxDate() {
        LocalDate result = null;
        if (options.keySet().contains(SECTION_FOR_GENERATOR_OPTIONS)) {
            if (((HashMap<String, Object>) options.get(
                    SECTION_FOR_GENERATOR_OPTIONS)).keySet().contains(
                            SUBSECTION_MAX_DATE)) {
                result = LocalDate.parse(
                        (String) ((HashMap<String, Object>) options.get(
                                SECTION_FOR_GENERATOR_OPTIONS)).get(
                                        SUBSECTION_MAX_DATE));
            }
        }
        return result;
    }

    /**
     * Sets the options structure.
     *
     * @param paramOptions
     *            the param options
     */
    public final void setOptions(final HashMap<String, Object> paramOptions) {
        options = paramOptions;
    }
}

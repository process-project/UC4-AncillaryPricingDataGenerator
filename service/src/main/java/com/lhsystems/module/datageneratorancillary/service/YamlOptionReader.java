/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service;

import java.io.InputStream;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Reads a Yaml Options file and returns the options.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class YamlOptionReader {

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
     * Specifies the key of the database option that contains how many objects
     * of each kind should be generated.
     */
    private static final String SUBSECTION_NUMBER_OF = "numberOf";

    /** An error message if a key is not contained in a map. */
    private static final String EXCEPTION_MESSAGE_PATTERN = "map doesn't contain key {0}";

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
    public String getDatabasePath() {
        final HashMap<String, Object> databaseOptions = (HashMap<String, Object>) get(
                options,
                SECTION_FOR_DATABASE_OPTIONS);
        return (String) get(databaseOptions, SUBSECTION_DATABASE_PATH);
    }

    /**
     * Returns how many objects of a given class should be generated according
     * to the options file.
     *
     * @param className
     *            the class name
     * @return the number of elements of the specified class
     */
    @SuppressWarnings("unchecked")
    public int getNumberOf(final String className) {
        final HashMap<String, Object> generatorOptions = (HashMap<String, Object>) get(
                options,
                SECTION_FOR_GENERATOR_OPTIONS);
        return (int) get(
                (HashMap<String, Object>) get(
                        generatorOptions,
                        SUBSECTION_NUMBER_OF),
                className);
    }

    /**
     * Gets the minimum date from the loaded file.
     *
     * @return the min date
     */
    @SuppressWarnings("unchecked")
    public LocalDate getMinDate() {
        final HashMap<String, Object> generatorOptions = (HashMap<String, Object>) get(
                options,
                SECTION_FOR_GENERATOR_OPTIONS);
        return LocalDate.parse(
                (String) get(generatorOptions, SUBSECTION_MIN_DATE));
    }

    /**
     * Gets the maximum date from the loaded file.
     *
     * @return the max date
     */
    @SuppressWarnings("unchecked")
    public LocalDate getMaxDate() {
        final HashMap<String, Object> generatorOptions = (HashMap<String, Object>) get(
                options,
                SECTION_FOR_GENERATOR_OPTIONS);
        return LocalDate.parse(
                (String) get(generatorOptions, SUBSECTION_MAX_DATE));
    }

    /**
     * Returns an entry of a given map or throws an exception if the entry
     * doesn't exist.
     *
     * @param <T>
     *            the value type of the map
     * @param map
     *            the map
     * @param key
     *            the key
     * @return the value of the key in map
     */
    private <T> T get(final Map<String,T> map,  final String key){
        if (!map.containsKey(key)){
            throw new RuntimeException(MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, key));
        }
        return map.get(key);
    }

}

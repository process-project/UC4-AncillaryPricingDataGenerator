/*
 *
 */
package com.lhsystems.module.data.generator.ancillary;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.lhsystems.module.data.generator.ancillary.generator.configuration.GeneratorConfiguration;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * Reads a Yaml Options file and returns the options.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Component
public final class YamlOptionReader {

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
    private HashMap<String, Object> options;

    /**
     * Constructor. Reads a Yaml file of the form of options.yml.template.
     *
     * @param optionsPath
     *            Path of the Yaml file containing the options
     */
    @SuppressWarnings("unchecked")
    public void loadBasicOptions(final String optionsPath) {
        final InputStream input = getClass().getResourceAsStream(optionsPath);
        final Yaml yaml = new Yaml();

        options = (HashMap<String, Object>) yaml.load(input);
    }

    public GeneratorConfiguration readGeneratorOptions(final String generatorOptionsPath) {
        final InputStream input = getClass().getResourceAsStream(generatorOptionsPath);
        final Yaml yaml = new Yaml();
        return yaml.loadAs(input, GeneratorConfiguration.class);
    }


    /**
     * Returns the path of the database as stated in the loaded file.
     *
     * @return the path of the database as stated in the loaded file
     */
    @SuppressWarnings("unchecked")
    public String getDatabasePath() {
        return "C:/Users/nwuser/MySQLiteDB";
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

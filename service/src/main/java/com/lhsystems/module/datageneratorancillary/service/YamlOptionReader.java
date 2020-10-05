package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;

import static com.lhsystems.module.datageneratorancillary.service.CommandLineOptionsReader.DEFAULT_COMPARTMENT_FILE;
import static com.lhsystems.module.datageneratorancillary.service.CommandLineOptionsReader.DEFAULT_GENERATOR_OPTIONS_FILE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * Reads a Yaml file and returns the options.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

@Component
public final class YamlOptionReader {

    /*** Logger. ***/
    private final Logger log = LoggerFactory.getLogger(YamlOptionReader.class);

    /**
     * Instantiates a new yaml option reader. Default constructor to satisfy
     * checkstyle requirements.
     */
    private YamlOptionReader() {

    }

    /**
     * Reads a Yaml file of the form of generator-options.yml.template into proper classes.
     *
     * @param generatorOptionsPath
     *              Path of the Yaml file containing the options
     * @return Generator configuration class
     */
    GeneratorConfiguration readGeneratorOptions(final String generatorOptionsPath) {
        final InputStream input = getInputStreamFromArgument(generatorOptionsPath, DEFAULT_GENERATOR_OPTIONS_FILE);
        return getGeneratorConfiguration(input);
    }

    /**
     * Reads file if program argument was presented.
     *
     * @param generatorOptionsPath
     *                  file path from param
     * @param defaultFile
     *                  default file path with options
     * @return InputStream if program argument was presented, null otherwise
     */
    private InputStream getInputStreamFromArgument(final String generatorOptionsPath, final String defaultFile) {
        try {
            if (!defaultFile.equals(generatorOptionsPath)) {
                return new FileInputStream(generatorOptionsPath);
            }
        } catch (final FileNotFoundException e) {
            log.error("Cannot find file from program argument", e.getMessage());
        }
        return getClass().getResourceAsStream(defaultFile);
    }

    /**
     * Get generator configuration. If there is any problem, return default options.
     *
     * @param input
     *           InputStream from which should read file
     * @return Generator configuration class
     */
    private GeneratorConfiguration getGeneratorConfiguration(final InputStream input) {
        final Yaml yaml = new Yaml();
        return yaml.loadAs(input, GeneratorConfiguration.class);
    }

    /**
     * Read a yaml file of the form of compartments.yml.template containing
     * compartments.
     *
     * @param compartmentsPath
     *            the compartments path
     * @return a list of compartments described in the file
     */
    public List<Compartment> readCompartments(final String compartmentsPath) {
        final InputStream input = getInputStreamFromArgument(compartmentsPath, DEFAULT_COMPARTMENT_FILE);
        final Yaml yaml = new Yaml();
        final List<Compartment> compartments = new ArrayList<>();
        for (final Map<String, String> map : (ArrayList<Map<String, String>>) yaml.load(
                input)) {
            compartments.add(
                    new Compartment(
                            map.get("identifier").charAt(0),
                            map.get("name")));
        }
        return compartments;
    }
}

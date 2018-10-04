/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     *            Path of the Yaml file containing the options
     * @return Generator configuration class
     */
    public GeneratorConfiguration readGeneratorOptions(final String generatorOptionsPath) {
        final InputStream input = getClass().getResourceAsStream(generatorOptionsPath);
        final Yaml yaml = new Yaml();
        return yaml.loadAs(input, GeneratorConfiguration.class);
    }

    public List<Compartment> readCompartments(final String compartmentsPath) {
        final InputStream input = getClass().getResourceAsStream(
                compartmentsPath);
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

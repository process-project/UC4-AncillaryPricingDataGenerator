/*
 *
 */
package com.lhsystems.module.datageneratorancillary.service;

import java.io.InputStream;

import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
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
     * Reads a Yaml file of the form of generator-options.yml.template into proper classes.
     *
     * @param generatorOptionsPath
     *            Path of the Yaml file containing the options
     */
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
}

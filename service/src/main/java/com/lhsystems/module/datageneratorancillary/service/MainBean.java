package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.starter.GeneratorStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Starts generator with options read from yml file.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Component
public class MainBean {

    /**
     * Starts next generator classes in proper way.
     */
    private final GeneratorStarter generatorStarter;

    /**
     * Reads options from CommandLine.
     */
    private final CommandLineOptionsReader commandLineReader;

    /**
     * Reads options from Yaml files.
     */
    private final YamlOptionReader optionReader;

    /**
     * Reads ssim file for generate airports and routes.
     */
    private final SSIMFileReader ssimReader;

    /**
     * Instantiates a new Main bean.
     *
     * @param generatorStarterParam    the generator starter
     * @param commandLineOptionsReader the command line options reader
     * @param yamlOptionReader         the yaml option reader
     * @param ssimFileReader           the ssim file reader
     */
    @Autowired
    public MainBean(final GeneratorStarter generatorStarterParam,
            final CommandLineOptionsReader commandLineOptionsReader,
            final YamlOptionReader yamlOptionReader,
            final SSIMFileReader ssimFileReader) {
        generatorStarter = generatorStarterParam;
        commandLineReader = commandLineOptionsReader;
        optionReader = yamlOptionReader;
        ssimReader = ssimFileReader;
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * SQLite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object. Afterwards saves the
     * generated flights in a SQLite dataBase.
     *
     * @param args default parameter for main method. No use for now.
     */
    // CHECKSTYLE:OFF
    public void start(final String[] args) {
        // CHECKSTYLE:ON
        final String yamlPath = commandLineReader.getYamlPathFromCommandLine(args);
        final String ssimPath = commandLineReader.getSsimPathFromCommandLine(
                args);
        final String compartmentsPath = commandLineReader.getCompartmentsPathFromCommandLine(
                args);
        generateAirlines(yamlPath, compartmentsPath, ssimPath);
    }

    /**
     * Start airline generator with options read from Yaml file.
     *
     *
     * @param yamlOptionsPath
     *             path to .yml file where are generator options
     */
    private void generateAirlines(final String yamlOptionsPath,
            final String compartmentsPath, final String ssimPath) {
        final GeneratorConfiguration generatorConfiguration = optionReader.readGeneratorOptions(yamlOptionsPath);
        final List<String> ssimLines = ssimReader.getSsimFileLines(
                ssimPath);
        final List<Compartment> compartments = optionReader.readCompartments(
                compartmentsPath);
        generatorStarter.generateData(
                generatorConfiguration,
                ssimLines,
                compartments);
    }

}

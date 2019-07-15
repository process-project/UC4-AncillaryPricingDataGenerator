package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.GeneratorConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.starter.GeneratorStarter;
import com.lhsystems.module.datageneratorancillary.service.serializer.CoreBookingSerializer;
import com.lhsystems.module.datageneratorancillary.service.utils.PathOptions;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * Reads ssim file to generate airports and routes.
     */
    private final SSIMFileReader ssimReader;

    /** Flag for generating infinity entities. */
    private final Boolean infiniteLoop;


    /** Logger.*/
    private final Logger log = LoggerFactory.getLogger(MainBean.class);



    /** Logger.*/
    private final Logger log = LoggerFactory.getLogger(MainBean.class);


    /**
     * Instantiates a new Main bean.
     * @param generatorStarterParam      the generator starter
     * @param commandLineOptionsReader   the command line options reader
     * @param yamlOptionReader           the yaml option reader
     * @param ssimFileReader             the ssim file reader
     * @param infiniteLoopParam          the environment variable for generating infinity entities
     */
    @Autowired
    public MainBean(final GeneratorStarter generatorStarterParam,
                    final CommandLineOptionsReader commandLineOptionsReader,
                    final YamlOptionReader yamlOptionReader,
                    final SSIMFileReader ssimFileReader,
                    @Value("${INFINITE_GENERATE}") final String infiniteLoopParam) {
        generatorStarter = generatorStarterParam;
        commandLineReader = commandLineOptionsReader;
        optionReader = yamlOptionReader;
        ssimReader = ssimFileReader;
        infiniteLoop = Boolean.valueOf(infiniteLoopParam);
    }

    /**
     * Reads options needed for Generation out of a Yaml file as well as a
     * SQLite database. Generates a number of flights specified in the Yaml file
     * by using a <code>flightGenerator</code> object.
     *
     * @param args default parameter for main method. No use for now.
     */
    // CHECKSTYLE:OFF
    public void start(final String[] args) {
        // CHECKSTYLE:ON
        final PathOptions pathOptions = commandLineReader.readPathOptionsFromCommandLine(
                args);
        long startTime = System.currentTimeMillis();
        generateAirlines(pathOptions);

        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Generation of all entities takes " + elapsedTime + " ms");

        while (infiniteLoop) {
            generateAirlines(pathOptions);
        }
    }

    /**
     * Start airline generator with options read from option files.
     *
     * @param pathOptions
     *            contains paths of option files
     */
    private void generateAirlines(final PathOptions pathOptions) {
        final GeneratorConfiguration generatorConfiguration = optionReader.readGeneratorOptions(
                pathOptions.getGeneratorConfigurationFile());
        final List<String> ssimLines = ssimReader.getSsimFileLines(
                pathOptions.getSsimFile());
        final List<Compartment> compartments = optionReader.readCompartments(
                pathOptions.getCompartmenFile());
        generatorStarter.generateBasicData(
                generatorConfiguration,
                ssimLines,
                compartments);
    }
}

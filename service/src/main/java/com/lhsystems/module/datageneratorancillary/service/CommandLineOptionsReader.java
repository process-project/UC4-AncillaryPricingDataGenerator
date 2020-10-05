package com.lhsystems.module.datageneratorancillary.service;

import com.lhsystems.module.datageneratorancillary.service.utils.PathOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class used for reading command line options.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Service
public final class CommandLineOptionsReader {


    /** The compartment options Path to be used if no path option is given. */
    static final String DEFAULT_COMPARTMENT_FILE = "/compartments.yml";

    /**
     * The generator options Path to be used if no path option is given.
     */
    static final String DEFAULT_GENERATOR_OPTIONS_FILE = "/generator-options.yml";

    /**
     * The SSIM Path to be used if no path option is given.
     */
    private static final String DEFAULT_SSIM_FILE = "/schedule-small.ssim";

    /**
     * Name of the option giving the yaml File containing the compartments.
     */
    private static final String OPTION_NAME_COMPARTMENT_FILE = "compartmentsFile";

    /**
     * Name of the option giving the ssim File containing the Airports.
     */
    private static final String OPTION_NAME_SSIM_FILE = "ssimFile";

    /**
     * Name of the option giving the yaml File containing the generator options.
     */
    private static final String OPTION_NAME_GENERATOR_CONFIGURATION_FILE = "generatorConfigurationFile";

    /** The options. */
    private final Options commandLineOptions;

    /** Logger.*/
    private final Logger log = LoggerFactory.getLogger(CommandLineOptionsReader.class);

    /**
     * Instantiates a new command line options reader.
     */
    private CommandLineOptionsReader() {
        commandLineOptions = new Options();
        commandLineOptions.addOption(
                OPTION_NAME_GENERATOR_CONFIGURATION_FILE,
                true,
                "Path of the generator options file. Defaults to "
                        + DEFAULT_GENERATOR_OPTIONS_FILE);
        commandLineOptions.addOption(
                OPTION_NAME_SSIM_FILE,
                true,
                "Path of the ssim options file. Defaults to "
                        + DEFAULT_SSIM_FILE);
        commandLineOptions.addOption(
                OPTION_NAME_COMPARTMENT_FILE,
                true,
                "Path of the compartments file. Defaults to "
                        + DEFAULT_COMPARTMENT_FILE);
    }

    /**
     * Read path files of all option files from command line.
     *
     * @param args
     *            the args
     * @return the path options
     */
    public PathOptions readPathOptionsFromCommandLine(final String[] args) {
        String compartmentFile = DEFAULT_COMPARTMENT_FILE;
        String ssimFile = DEFAULT_SSIM_FILE;
        String generatorConfigurationFile = DEFAULT_GENERATOR_OPTIONS_FILE;
        try {
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(commandLineOptions, args);
            if (line.hasOption(OPTION_NAME_COMPARTMENT_FILE)) {
                compartmentFile = line.getOptionValue(
                        OPTION_NAME_COMPARTMENT_FILE);
            }
            if (line.hasOption(OPTION_NAME_SSIM_FILE)) {
                ssimFile = line.getOptionValue(OPTION_NAME_SSIM_FILE);
            }
            if (line.hasOption(OPTION_NAME_GENERATOR_CONFIGURATION_FILE)) {
                generatorConfigurationFile = line.getOptionValue(
                        OPTION_NAME_GENERATOR_CONFIGURATION_FILE);
            }
        } catch (final ParseException e) {
            log.error("Cannot parse argument from commandLine", e.getMessage());
        }
        return new PathOptions(
                compartmentFile,
                ssimFile,
                generatorConfigurationFile);
    }
}

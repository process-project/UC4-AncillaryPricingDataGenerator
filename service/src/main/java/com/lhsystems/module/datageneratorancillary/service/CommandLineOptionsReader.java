package com.lhsystems.module.datageneratorancillary.service;

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


    private final Options cmdOptions;

    private static final String DEFAULT_COMPARTMENT_FILE = "/compartments.yml";

    private static final String DEFAULT_SSIM_FILE = "/schedule-small.ssim";

    /**
     * The Path to be used if no path option is given.
     */
    private static final String DEFAULT_YAML_FILE = "/generator-options.yml";

    /** Logger.*/
    private final Logger log = LoggerFactory.getLogger(CommandLineOptionsReader.class);

    private static final String OPTION_NAME_COMPARTMENT_FILE = "compartmentsFile";

    /**
     * Name of the option giving the ssim File containing the Airports.
     */
    private static final String OPTION_NAME_SSIM_FILE = "ssimFile";

    /**
     * Name of the option giving the yaml File containing the options.
     */
    private static final String OPTION_NAME_YAML_FILE = "yamlFile";

    /**
     * Instantiates a new command line options reader.
     */
    private CommandLineOptionsReader() {
        cmdOptions = new Options();
        cmdOptions.addOption(
                OPTION_NAME_YAML_FILE,
                true,
                "Path of the yaml options file. Defaults to "
                        + DEFAULT_YAML_FILE);
        cmdOptions.addOption(
                OPTION_NAME_SSIM_FILE,
                true,
                "Path of the ssim options file. Defaults to "
                        + DEFAULT_SSIM_FILE);
        cmdOptions.addOption(
                OPTION_NAME_COMPARTMENT_FILE,
                true,
                "Path of the compartments file. Defaults to "
                        + DEFAULT_COMPARTMENT_FILE);
    }

    /**
     * Parses arguments to find request option.
     *
     * @param args
     *            program arguments
     * @return value of command line option FILE_OPTIONS_NAME or
     *         DEFAULT_OPTIONS_FILE when option not found or in case of error
     */
    public String getYamlPathFromCommandLine(final String[] args) {
        try {
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(cmdOptions, args);
            if (line.hasOption(OPTION_NAME_YAML_FILE)) {
                return line.getOptionValue(OPTION_NAME_YAML_FILE);
            }
        } catch (final ParseException e) {
            log.error("Cannot parse argument from commandLine", e.getMessage());
        }
        return DEFAULT_YAML_FILE;
    }

    public String getSsimPathFromCommandLine(final String[] args) {
        try {
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(cmdOptions, args);
            if (line.hasOption(OPTION_NAME_SSIM_FILE)) {
                return line.getOptionValue(OPTION_NAME_SSIM_FILE);
            }
        } catch (final ParseException e) {
            log.error("Cannot parse argument from commandLine", e.getMessage());
        }
        return DEFAULT_SSIM_FILE;
    }

    public String getCompartmentsPathFromCommandLine(final String[] args) {
        try {
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(cmdOptions, args);
            if (line.hasOption(OPTION_NAME_COMPARTMENT_FILE)) {
                return line.getOptionValue(OPTION_NAME_COMPARTMENT_FILE);
            }
        } catch (final ParseException e) {
            log.error("Cannot parse argument from commandLine", e.getMessage());
        }
        return DEFAULT_COMPARTMENT_FILE;
    }
}

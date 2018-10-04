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


    /** Logger.*/
    private final Logger log = LoggerFactory.getLogger(CommandLineOptionsReader.class);

    /** The name of the option that states if ansi coloring should be used. */
    private final String OPTION_NAME_SPRING_OUTPUT_ANSI_ENABLED = "spring.output.ansi.enabled";

    /**
     * Name of the option giving the yaml File containing the options.
     */
    private final String OPTION_NAME_OPTIONS_FILE = "optionsFile";

    /**
     * The Path to be used if the path option is not given.
     */
    private final String DEFAULT_OPTIONS_FILE = "/generator-options.yml";

    /**
     * Instantiates a new command line options reader. Default constructor to
     * satisfy checkstyle requirements.
     */
    private CommandLineOptionsReader() {

    }

    /**
     * Reads the command line option FILE_OPTIONS_NAME and returns its value.
     *
     * @param args default parameter for parsing, no use for now
     * @return value of command line option FILE_OPTIONS_NAME
     */
    public String getYamlPathFromCommandLine(final String[] args)  {
        final Options cmdOptions = new Options();
        cmdOptions.addOption(OPTION_NAME_OPTIONS_FILE, true,
                "Path of the yaml options file. Defaults to " + DEFAULT_OPTIONS_FILE);
        return getPathFromCommandLine(cmdOptions, args);
    }

    /**
     * Parses arguments to find request option.
     *
     * @param cmdOptions
     *            options used for parsing arguments from command line
     * @param args
     *            program arguments
     * @return value of command line option FILE_OPTIONS_NAME
     * or DEFAULT_OPTIONS_FILE when option not found or in case of error
     */
    private String getPathFromCommandLine(final Options cmdOptions, final String[] args){
        try {
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(cmdOptions, args);
            if (line.hasOption(OPTION_NAME_OPTIONS_FILE)) {
                return line.getOptionValue(OPTION_NAME_OPTIONS_FILE);
            }
        } catch (final ParseException e) {
            log.error("Cannot parse argument from commandLine", e.getMessage());
        }
        return DEFAULT_OPTIONS_FILE;
    }
}

package com.lhsystems.module.datageneratorancillary.service;

/**
 * Contains paths of option files.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class PathOptions {

    /** The path of a yaml file containing compartments. */
    private final String compartmentFile;

    /** The path of a ssim file. */
    private final String ssimFile;

    /** The path of a yaml file containing generator configurations. */
    private final String generatorConfigurationFile;

    /**
     * Instantiates a new path options object.
     *
     * @param paramCompartmenFile
     *            the param compartmen file
     * @param paramSsimFile
     *            the param ssim file
     * @param paramGeneratorConfigurationFile
     *            the param generator configuration file
     */
    public PathOptions(final String paramCompartmenFile,
            final String paramSsimFile,
            final String paramGeneratorConfigurationFile) {
        compartmentFile = paramCompartmenFile;
        ssimFile = paramSsimFile;
        generatorConfigurationFile = paramGeneratorConfigurationFile;
    }

    /**
     * Gets the path of the stored compartment file.
     *
     * @return the compartment file
     */
    public String getCompartmenFile() {
        return compartmentFile;
    }

    /**
     * Gets the path of the stored ssim file.
     *
     * @return the ssim file
     */
    public String getSsimFile() {
        return ssimFile;
    }

    /**
     * Gets the path of the stored generator configuration file.
     *
     * @return the generator configuration file
     */
    public String getGeneratorConfigurationFile() {
        return generatorConfigurationFile;
    }

}

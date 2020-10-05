package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for baggage class generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BaggageClassConfiguration {

    /** Number of how many baggage class should be generated. */
    private int numberBaggageClass;

    /**
     * Instantiates a new baggage configuration object.
     */
    public BaggageClassConfiguration() {
    }

    /**
     * Gets the size of baggage classes.
     * @return
     *      baggage classes
     */
    public int getNumberBaggageClass() {
        return numberBaggageClass;
    }

    /**
     * Set the size of baggage class, used for reading yml file.
     *
     * @param baggageClassParam
     *        baggageClass from zml file
     */
    public void setNumberBaggageClass(final int baggageClassParam) {
        numberBaggageClass = baggageClassParam;
    }
}

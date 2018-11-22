package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for tariff generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class TariffConfiguration {

    /** The maximum price of a generated tariff. */
    private double maximumPrice;

    /** The minimum price of a generated tariff. */
    private double minimumPrice;

    /** The number of tariffs to be generated. */
    private int numberTariff;

    /**
     * Instantiates a new tariff configuration.
     */
    public TariffConfiguration() {
    }

    /**
     * Gets the maximum price.
     *
     * @return the maximum price
     */
    public double getMaximumPrice() {
        return maximumPrice;
    }

    /**
     * Gets the minimum price.
     *
     * @return the minimum price
     */
    public double getMinimumPrice() {
        return minimumPrice;
    }

    /**
     * Gets the number of tariffs.
     *
     * @return the number of tariffs
     */
    public int getNumberTariff() {
        return numberTariff;
    }

    /**
     * Sets the maximum price.
     *
     * @param paramMaximumPrice
     *            the new maximum price
     */
    public void setMaximumPrice(final double paramMaximumPrice) {
        this.maximumPrice = paramMaximumPrice;
    }

    /**
     * Sets the minimum price.
     *
     * @param paramMinimumPrice
     *            the new minimum price
     */
    public void setMinimumPrice(final double paramMinimumPrice) {
        this.minimumPrice = paramMinimumPrice;
    }

    /**
     * Sets the number tariff.
     *
     * @param paramNumberTariff
     *            the new number tariff
     */
    public void setNumberTariff(final int paramNumberTariff) {
        this.numberTariff = paramNumberTariff;
    }

}

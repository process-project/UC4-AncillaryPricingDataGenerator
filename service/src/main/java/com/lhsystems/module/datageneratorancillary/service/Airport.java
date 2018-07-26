package com.lhsystems.module.datageneratorancillary.service;

/**
 * The class Airport serves as a model for airports.
 *
 * @author Janek Reichardt
 * @version $Revision: 1.10 $
 */

final public class Airport {

    /**
     * Pattern that every iata airport code should match.
     */
    private static String PATTERN_IATA_AIRPORT_CODE = "[A-Z]{3}";

    /**
     * IATA airport code naming and identifying the airport.
     */
    private String iataCode;

    /**
     * Full name of the airport.
     */
    private final String name;

    /**
     * Market in which the airport lies in.
     */
    private final Market market;

    /**
     * Constructor.
     */
    public Airport(final String iataCode, final String name,
            final Market market) {
        this.name = name;
        this.market = market;
        setIataCode(iataCode);
    }

    /**
     * Returns the market of the airport object.
     *
     * @return the market of the airport
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Returns the name of the airport object.
     *
     * @return the name of the airport
     */
    public String getName() {
        return name;
    }

    /**
     * Sets <code>iataCode</code> of this Airport if pattern is correct, throws
     * exception otherwise.
     *
     * @param iataCode
     */
    private void setIataCode(final String iataCode) {
        if (iataCode.toUpperCase().matches(PATTERN_IATA_AIRPORT_CODE)) {
            this.iataCode = iataCode.toUpperCase();
        } else {
            throw new RuntimeException("Incorrect Iata Code:" + iataCode);
        }
    }

    /**
     * Returns the iata code of the airport object.
     *
     * @return the iata code of the airpor
     */
    public String getIata() {
        return iataCode;
    }

}

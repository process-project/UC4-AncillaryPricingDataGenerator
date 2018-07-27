package com.lhsystems.module.datageneratorancillary.service;

/**
 * The class Airport serves as a model for airports.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */

public final class Airport {

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
     * Instantiates a new airport.
     *
     * @param paramIataCode
     *            IATA code of the airport.
     * @param paramName
     *            full name of the airport.
     * @param paramMarket
     *            the market in which the airport lies in.
     */
    public Airport(final String paramIataCode, final String paramName,
            final Market paramMarket) {
        name = paramName;
        market = paramMarket;
        setIataCode(paramIataCode);
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
     * @param paramIataCode
     *            new IATA code of the airport
     */
    private void setIataCode(final String paramIataCode) {
        if (paramIataCode.toUpperCase().matches(PATTERN_IATA_AIRPORT_CODE)) {
            iataCode = paramIataCode.toUpperCase();
        } else {
            throw new RuntimeException("Incorrect Iata Code:" + paramIataCode);
        }
    }

    /**
     * Returns the IATA code of the airport object.
     *
     * @return the IATA code of the airport
     */
    public String getIata() {
        return iataCode;
    }

}

package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Store configuration responsible for flight generator.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class FlightConfiguration {
    /** The maximum date of departure flight.*/
    private Date maximumFlightDate;

    /** The maximum number of bookable tariffs for one flight. */
    private int maximumNumberTariffs;

    /** The minimum date of departure flight.*/
    private Date minimumFlightDate;

    /** The minimum number of bookable tariffs for one flight. */
    private int minimumNumberTariffs;

    /** Number of flights that should be generated. */
    private long numberFlight;


    /**
     * Instantiates a new flight generator configuration object.
     */
    public FlightConfiguration() {
    }

    /**
     * Gets the max flight date.
     *
     * @return
     *      max flight date as local date
     */
    public LocalDate getMaximumFlightDateAsLocalDate() {
        return maximumFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Gets the maximum number tariffs.
     *
     * @return the maximum number tariffs
     */
    public int getMaximumNumberTariffs() {
        return maximumNumberTariffs;
    }

    /**
     * Gets the min flight date .
     * @return
     *      min flight date as local date
     */
    public LocalDate getMinimumFlightDateAsLocalDate() {
        return minimumFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Gets the minimum number tariffs.
     *
     * @return the minimum number tariffs
     */
    public int getMinimumNumberTariffs() {
        return minimumNumberTariffs;
    }

    /**
     * Gets the number of flights.
     *
     * @return
     *      number of flights
     */
    public long getNumberFlight() {
        return numberFlight;
    }

    /**
     * Set max flight date, used for reading yml file.
     *
     * @param maxFlightDateParam
     *        maxFlightDate from zml file
     */
    public void setMaximumFlightDate(final Date maxFlightDateParam) {
        maximumFlightDate = maxFlightDateParam;
    }

    /**
     * Sets the maximum number tariffs.
     *
     * @param paramMaximumNumberTariffs
     *            the new maximum number tariffs
     */
    public void setMaximumNumberTariffs(final int paramMaximumNumberTariffs) {
        maximumNumberTariffs = paramMaximumNumberTariffs;
    }

    /**
     * Set min flight date, used for reading yml file.
     *
     * @param minFlightDateParam
     *        minFlightDate from zml file
     */
    public void setMinimumFlightDate(final Date minFlightDateParam) {
        minimumFlightDate = minFlightDateParam;
    }

    /**
     * Sets the minimum number tariffs.
     *
     * @param paramMinimumNumberTariffs
     *            the new minimum number tariffs
     */
    public void setMinimumNumberTariffs(final int paramMinimumNumberTariffs) {
        minimumNumberTariffs = paramMinimumNumberTariffs;
    }

    /**
     * Set max number of flights, used for reading yml file.
     *
     * @param numberParam
     *            number from zml file
     */
    public void setNumberFlight(final long numberParam) {
        numberFlight = numberParam;
    }
}

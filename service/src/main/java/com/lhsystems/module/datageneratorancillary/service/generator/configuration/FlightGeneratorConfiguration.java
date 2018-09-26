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
public class FlightGeneratorConfiguration {
    /** The minimum date of departure flight.*/
    private Date minFlightDate;

    /** The maximum date of departure flight.*/
    private Date maxFlightDate;

    /** Number of flights that should be generated. */
    private int number;

    /**
     * Gets the min flight date .
     * @return
     *      min flight date as local date
     */
    public LocalDate getMinFlightDateAsLocalDate() {
        return minFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Set min flight date, used for reading yml file.
     *
     * @param minFlightDateParam
     *        minFlightDate from zml file
     */
    public void setMinFlightDate(final Date minFlightDateParam) {
        this.minFlightDate = minFlightDateParam;
    }

    /**
     * Gets the max flight date.
     *
     * @return
     *      max flight date as local date
     */
    public LocalDate getMaxFlightDateAsLocalDate() {
        return maxFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Set max flight date, used for reading yml file.
     *
     * @param maxFlightDateParam
     *        maxFlightDate from zml file
     */
    public void setMaxFlightDate(final Date maxFlightDateParam) {
        this.maxFlightDate = maxFlightDateParam;
    }

    /**
     * Gets the number of flights.
     *
     * @return
     *      number of flights
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set max number of flights, used for reading yml file.
     *
     * @param numberParam
     *        number from zml file
     */
    public void setNumber(final int numberParam) {
        this.number = numberParam;
    }
}

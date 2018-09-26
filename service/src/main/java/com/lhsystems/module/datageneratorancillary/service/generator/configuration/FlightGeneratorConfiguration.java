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
    /** The minimum date of departure flight*/
    private Date minFlightDate;

    /** The maximum date of departure flight*/
    private Date maxFlightDate;

    /** Number of flights that should be generated. */
    private int number;

    public LocalDate getMinFlightDateAsLocalDate() {
        return minFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setMinFlightDate(Date minFlightDate) {
        this.minFlightDate = minFlightDate;
    }

    public LocalDate getMaxFlightDateAsLocalDate() {
        return maxFlightDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void setMaxFlightDate(Date maxFlightDate) {
        this.maxFlightDate = maxFlightDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

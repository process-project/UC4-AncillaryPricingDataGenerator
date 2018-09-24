package com.lhsystems.module.data.generator.ancillary.generator.configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FlightGeneratorConfiguration {
    private Date minFlightDate;
    private Date maxFlightDate;
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

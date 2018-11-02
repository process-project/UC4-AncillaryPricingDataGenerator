package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.CoreBookingConfiguration;

import java.util.List;

/**
 * Generates CoreBooking objects randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class CoreBookingGenerator extends DataGenerator {

    /** The flights of which we chose during booking generation. */
    private final List<Flight> flights;

    /** The maximum number of passengers. */
    private final int maximumNumberPassengers;

    /** The minimum number of passengers. */
    private final int minimumNumberPassengers;

    /** The range of days before departure. */
    private final int rangeOfDaysBeforeDeparture;

    /**
     * Instantiates a new core booking generator.
     *
     * @param paramFlights
     *            the param flights
     * @param coreBookingConfiguration
     *            the core booking configuration
     */
    public CoreBookingGenerator(final List<Flight> paramFlights,
            final CoreBookingConfiguration coreBookingConfiguration) {
        flights = paramFlights;
        maximumNumberPassengers = coreBookingConfiguration.getMaximumNumberPassengers();
        minimumNumberPassengers = coreBookingConfiguration.getMinimumNumberPassengers();
        rangeOfDaysBeforeDeparture = coreBookingConfiguration.getRangeOfDaysBeforeDeparture();
    }

    @Override
    protected CoreBooking generate() {
        final Flight flight = getRandom().getOneRandomElement(flights);
        final Tariff tariff = getRandom().getOneRandomElement(
                flight.getBookableTariffs());
        final double shape;
        final double scale;
        if (tariff.getMarket().compareTo(Market.DOMESTIC) <= 0) {
            shape = 1.151;
            scale = 1 / 0.046;
        } else {
            shape = 0.989;
            scale = 1 / 0.014;
        }
        final int daysBeforeDeparture = (int) getRandom().getCutOffGammaDistributedDouble(
                0,
                rangeOfDaysBeforeDeparture,
                0,
                shape,
                scale);
        final int numberPassengers = getRandom().nextInt(
                minimumNumberPassengers,
                maximumNumberPassengers + 1);
        return new CoreBooking(
                daysBeforeDeparture,
                flight,
                numberPassengers,
                tariff);
    }

}

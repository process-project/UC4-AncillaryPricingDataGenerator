package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.BaggageSelection;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.SeatSelection;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.util.Precision;

/**
 * Generates Bookings.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BookingGenerator extends DataGenerator {

    /** The minimum number of passengers. */
    private static final int MINIMUM_NUMBER_PASSENGERS = 1;

    /** The maximum number of passengers. */
    private static final int MAXIMUM_NUMBER_PASSENGERS = 5;

    /** The minimum number of bags. */
    private static final int MINIMUM_NUMBER_BAGS = 0;

    /** The maximum number of bags. */
    private static final int MAXIMUM_NUMBER_BAGS = 2;

    /** The flights of which we chose during booking generation. */
    private final List<Flight> flights;

    /**
     * Instantiates a new complete booking generator.
     *
     * @param paramFlights
     *            the flights of wich bookings are chosen
     */
    public BookingGenerator(final List<Flight> paramFlights) {
        flights = paramFlights;
    }

    /**
     * Creates the seat selection.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number passengers
     * @param bookingDaysBeforeDeparture
     *            the days before departure of the respective booking
     * @return the seat selection
     */
    public SeatSelection createSeatSelection(final Tariff tariff,
            final int numberPassengers, final int bookingDaysBeforeDeparture) {
        final HashMap<SeatGroup, Integer> chosenSeats = new HashMap<>();
        final List<SeatGroup> seatGroups = tariff.getSeating().getSeatGroups();
        for (final SeatGroup seatGroup : seatGroups) {
            chosenSeats.put(seatGroup, 0);
        }
        for (int passengerCounter = 0; passengerCounter < numberPassengers; passengerCounter++) {
            final SeatGroup chosenGroup = seatGroups.get(
                    getRandom().nextInt(chosenSeats.size()));
            chosenSeats.put(chosenGroup, chosenSeats.get(chosenGroup) + 1);
        }
        final int seatDaysBeforeDeparture = getRandomDaysBeforeDeparture(
                bookingDaysBeforeDeparture,
                tariff.getMarket());
        return new SeatSelection(chosenSeats, seatDaysBeforeDeparture);
    }

    /**
     * Creates the baggage selection.
     *
     * @param tariff
     *            the tariff
     * @param bookingDaysBeforeDeparture
     *            the days before departure of the respective booking
     * @return the baggage selection
     */
    public BaggageSelection createBaggageSelection(final Tariff tariff,
            final int bookingDaysBeforeDeparture) {
        final HashMap<BaggageClass, Integer> chosenBags = new HashMap<>();
        final List<BaggageClass> baggageClasses = tariff.getProduct().getBaggageClasses();
        final Map<BaggageClass, Integer> includedBags = tariff.getProduct().getNumberOfIncludedBagsByBaggageClass();
        final int numberBags = getRandom().nextInt(
                Math.max(
                        MINIMUM_NUMBER_BAGS,
                        includedBags.values().stream().mapToInt(
                                Integer::intValue).sum()),
                Math.max(
                        MAXIMUM_NUMBER_BAGS,
                        includedBags.values().stream().mapToInt(
                                Integer::intValue).sum())
                + 1);
        for (final BaggageClass baggageClass : baggageClasses) {
            chosenBags.put(baggageClass, includedBags.get(baggageClass));
        }
        for (int bagCounter = includedBags.values().stream().mapToInt(
                Integer::intValue).sum(); bagCounter < numberBags; bagCounter++) {
            final BaggageClass chosenBaggageClass = baggageClasses.get(
                    getRandom().nextInt(baggageClasses.size()));
            chosenBags.put(
                    chosenBaggageClass,
                    chosenBags.get(chosenBaggageClass) + 1);
        }
        final int baggageDaysBeforeDeparture = getRandomDaysBeforeDeparture(
                bookingDaysBeforeDeparture,
                tariff.getMarket());
        return new BaggageSelection(chosenBags, baggageDaysBeforeDeparture);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object generate() {
        final Flight flight = getRandom().getOneRandomElement(flights);
        final Tariff tariff = getRandom().getOneRandomElement(
                flight.getBookableTariffs());
        final int daysBeforeDeparture = getRandomDaysBeforeDeparture(
                100,
                flight.getRoute().getMarket());
        final int numberPassengers = getRandom().nextInt(
                MINIMUM_NUMBER_PASSENGERS,
                MAXIMUM_NUMBER_PASSENGERS);
        final CoreBooking coreBooking = new CoreBooking(
                daysBeforeDeparture,
                flight,
                numberPassengers,
                tariff);
        final SeatSelection seatSelection = createSeatSelection(
                tariff,
                numberPassengers,
                daysBeforeDeparture);
        final BaggageSelection baggageSelection = createBaggageSelection(
                tariff,
                daysBeforeDeparture);
        return new Booking(
                coreBooking,
                seatSelection,
                baggageSelection);
    }

    /**
     * Pulls a "day before Departure" from a modified Gamma Distribution.
     *
     * @param max
     *            the max
     * @param market
     *            the market
     * @return the random days before departure
     */
    private int getRandomDaysBeforeDeparture(final int max,
            final Market market) {
        final Double shape;
        final Double scale;
        if (market.compareTo(Market.DOMESTIC) <= 0) {
            shape = 1.151;
            scale = 1 / 0.046;
        } else {
            shape = 0.989;
            scale = 1 / 0.014;
        }
        return (int) Math.min(
                max,
                Precision.round(
                        new GammaDistribution(shape, scale).sample(),
                        0));
    }
}

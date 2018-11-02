package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceSelection;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.BookingConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.util.Precision;

/**
 * Generates Bookings randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BookingGenerator extends DataGenerator {

    /** The flights of which we chose during booking generation. */
    private final List<Flight> flights;

    /** The maximum number of bags per passenger. */
    private final int maximumNumberBags;

    /** The maximum number of passengers. */
    private final int maximumNumberPassengers;

    /** The minimum number of bags per passenger. */
    private final int minimumNumberBags;

    /** The minimum number of passengers. */
    private final int minimumNumberPassengers;

    /** The range of days before departure. */
    private final int rangeOfDaysBeforeDeparture;

    /**
     * Instantiates a new booking generator.
     *
     * @param paramFlights
     *            the flights of wich bookings are chosen
     * @param bookingConfiguration
     *            the booking configuration
     */
    public BookingGenerator(final List<Flight> paramFlights, final BookingConfiguration bookingConfiguration) {
        flights = paramFlights;
        minimumNumberBags = bookingConfiguration.getMinimumNumberBags();
        maximumNumberBags = bookingConfiguration.getMaximumNumberBags();
        minimumNumberPassengers = bookingConfiguration.getMinimumNumberPassengers();
        maximumNumberPassengers = bookingConfiguration.getMaximumNumberPassengers();
        rangeOfDaysBeforeDeparture = bookingConfiguration.getRangeOfDaysBeforeDeparture();
    }


    /**
     * chooses baggage for a booking.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number of passengers booking baggage
     * @return the baggage selection
     */
    private HashMap<Service, Integer> chooseBaggage(
            final Tariff tariff, final int numberPassengers) {
        final HashMap<Service, Integer> chosenBags = new HashMap<>();
        final List<Service> baggageClasses = tariff.getProduct().getServicesByClass(
                BaggageClass.class);
        final Map<BaggageClass, Integer> includedBags = tariff.getProduct().getNumberOfIncludedBagsByBaggageClass();
        final int numberBags = getRandom().nextInt(
                Math.max(
                        minimumNumberBags * numberPassengers,
                        includedBags.values().stream().mapToInt(
                                Integer::intValue).sum()),
                Math.max(
                        maximumNumberBags * numberPassengers,
                        includedBags.values().stream().mapToInt(
                                Integer::intValue).sum())
                + 1);
        for (final Service baggageClass : baggageClasses) {
            chosenBags.put(
                    baggageClass,
                    includedBags.get(baggageClass));
        }
        for (int bagCounter = includedBags.values().stream().mapToInt(
                Integer::intValue).sum(); bagCounter < numberBags; bagCounter++) {
            final Service chosenBaggageClass = baggageClasses.get(
                    getRandom().nextInt(baggageClasses.size()));
            chosenBags.put(
                    chosenBaggageClass,
                    chosenBags.get(chosenBaggageClass) + 1);
        }
        for (final Service baggageClass : baggageClasses) {
            if (chosenBags.get(baggageClass) == 0) {
                chosenBags.remove(baggageClass);
            }
        }
        return chosenBags;
    }


    /**
     * Choose seats for a given number of passengers.
     *
     * @param seatGroups
     *            the seat groups being offered
     * @param numberPassengers
     *            the number passengers
     * @return the seat selection
     */
    private HashMap<Service, Integer> chooseSeats(
            final List<Service> seatGroups,
            final int numberPassengers) {
        final HashMap<Service, Integer> chosenSeats = new HashMap<>();
        for (final Service seatGroup : seatGroups) {
            chosenSeats.put(seatGroup, 0);
        }

        for (int passengerCounter = 0; passengerCounter < numberPassengers; passengerCounter++) {
            final Service chosenGroup = seatGroups.get(
                    getRandom().nextInt(chosenSeats.size()));
            chosenSeats.put(chosenGroup, chosenSeats.get(chosenGroup) + 1);
        }
        for (final Service seatGroup : seatGroups) {
            if (chosenSeats.get(seatGroup) == 0) {
                chosenSeats.remove(seatGroup);
            }
        }
        return chosenSeats;
    }


    /**
     * Selects some Baggage for a booking.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number passengers
     * @param daysBeforeDeparture
     *            the time of booking in days before departure
     * @return the service selection
     */
    private ServiceSelection createBaggageSelection(final Tariff tariff,
            final int numberPassengers, final int daysBeforeDeparture) {
        final HashMap<Service, Integer> chosenBaggage = chooseBaggage(
                tariff,
                numberPassengers);
        final int seatDaysBeforeDeparture = getRandomDaysBeforeDeparture(
                daysBeforeDeparture,
                tariff.getMarket());

        final HashMap<Service, Integer> bagDBD = new HashMap<>();
        for (final Service baggageClass : chosenBaggage.keySet()) {
            bagDBD.put(baggageClass, seatDaysBeforeDeparture);
        }
        final HashMap<Service, Double> prices = new HashMap<>();
        for (final Service baggageClass : chosenBaggage.keySet()) {
            prices.put(
                    baggageClass,
                    baggageClass.getPrice(chosenBaggage.get(baggageClass)));
        }
        return new ServiceSelection(chosenBaggage, prices, bagDBD);
    }

    /**
     * Selects seats for a booking.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number passengers
     * @param daysBeforeDeparture
     *            the time of booking in days before departure
     * @return the service selection
     */
    private ServiceSelection createSeatSelection(final Tariff tariff,
            final int numberPassengers, final int daysBeforeDeparture) {
        final HashMap<Service, Integer> chosenSeats = chooseSeats(
                tariff.getProduct().getServicesByClass(SeatGroup.class),
                numberPassengers);
        final int seatDaysBeforeDeparture = getRandomDaysBeforeDeparture(
                daysBeforeDeparture,
                tariff.getMarket());
        final HashMap<Service, Integer> seatDBD = new HashMap<>();
        for (final Service seatGroup : chosenSeats.keySet()) {
            seatDBD.put(seatGroup, seatDaysBeforeDeparture);
        }
        final HashMap<Service, Double> prices = new HashMap<>();
        for (final Service seatGroup : chosenSeats.keySet()) {
            prices.put(
                    seatGroup,
                    seatGroup.getPrice(chosenSeats.get(seatGroup)));
        }
        return new ServiceSelection(chosenSeats, prices, seatDBD);

    }

    /**
     * Creates a service selection for a booking.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number of passengers
     * @param daysBeforeDeparture
     *            the time of booking in days before departure
     * @return the service selection
     */
    private ServiceSelection createServiceSelection(final Tariff tariff,
            final int numberPassengers, final int daysBeforeDeparture) {
        final ServiceSelection serviceSelection = createBaggageSelection(
                tariff,
                numberPassengers,
                daysBeforeDeparture);
        serviceSelection.add(
                createSeatSelection(
                        tariff,
                        numberPassengers,
                        daysBeforeDeparture));
        return serviceSelection;
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
                rangeOfDaysBeforeDeparture,
                flight.getRoute().getMarket());
        final int numberPassengers = getRandom().nextInt(
                minimumNumberPassengers,
                maximumNumberPassengers + 1);
        final CoreBooking coreBooking = new CoreBooking(
                daysBeforeDeparture,
                flight,
                numberPassengers,
                tariff);
        final ServiceSelection serviceSelection = createServiceSelection(
                tariff,
                numberPassengers,
                daysBeforeDeparture);
        return new Booking(
                coreBooking,
                serviceSelection);
    }

    /**
     * Draws a "day before Departure" from a modified Gamma Distribution.
     *
     * @param max
     *            the max
     * @param market
     *            the market
     * @return the randomized time of booking in days before departure
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

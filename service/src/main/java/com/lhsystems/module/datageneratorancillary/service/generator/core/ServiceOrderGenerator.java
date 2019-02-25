package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ServiceOrderConfiguration;
import com.lhsystems.module.datageneratorancillary.service.utils.ExtendedRandom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generates service orders randomly.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ServiceOrderGenerator {

    /** The maximum number of bags per passenger. */
    private final int maximumNumberBags;

    /** The minimum number of bags per passenger. */
    private final int minimumNumberBags;

    /**
     * Generates a stream of pseudo random numbers used for generating flights.
     */
    private final ExtendedRandom random = new ExtendedRandom();

    /** The probability of any service to be included in a service order. */
    private final double serviceProbability;

    /**
     * Instantiates a new service order generator.
     *
     * @param serviceOrderConfiguration
     *            the service order configuration
     */
    public ServiceOrderGenerator(
            final ServiceOrderConfiguration serviceOrderConfiguration) {
        maximumNumberBags = serviceOrderConfiguration.getMaximumNumberBags();
        minimumNumberBags = serviceOrderConfiguration.getMinimumNumberBags();
        serviceProbability = serviceOrderConfiguration.getServiceProbability();
    }

    /**
     * Generate orders of services added to a booking.
     *
     * @param coreBooking
     *            the core booking
     * @return the list
     */
    public List<ServiceOrder> generateOrders(final CoreBooking coreBooking) {
        final List<ServiceOrder> serviceOrders = generateBaggageOrders(
                coreBooking);
        serviceOrders.addAll(
                generateSeatOrders(coreBooking));
        serviceOrders.addAll(generateAdditionalServiceOrders(coreBooking));
        return serviceOrders;
    }

    /**
     * Choose bags randomly for a booking.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number of passengers
     * @return a hash map assigning the count to each baggage class
     */
    private HashMap<Service, Integer> chooseBags(final Tariff tariff,
            final int numberPassengers) {
        final HashMap<Service, Integer> chosenBags = new HashMap<>();
        final List<Service> baggageClasses = Service.getServicesByServiceClass(
                tariff.getProduct().getServices(),
                BaggageClass.class);
        final Map<BaggageClass, Integer> includedBags = tariff.getProduct().getNumberOfIncludedBagsByBaggageClass();
        final int numberBags = getNumberOfBags(tariff, numberPassengers);
        for (final Service baggageClass : baggageClasses) {
            chosenBags.put(
                    baggageClass,
                    numberPassengers * includedBags.get(baggageClass));
        }
        for (int bagCounter = includedBags.values().stream().mapToInt(
                Integer::intValue).sum()
                * numberPassengers; bagCounter < numberBags; bagCounter++) {
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
     * Generate additional service orders besides seats and baggage.
     *
     * @param coreBooking
     *            the core booking
     * @return the list
     */
    private List<ServiceOrder> generateAdditionalServiceOrders(
            final CoreBooking coreBooking) {
        final List<Service> offeredServices = coreBooking.getTariff().getProduct().getServices();
        final List<ServiceOrder> serviceOrders = new ArrayList<>();
        final double shape;
        final double scale;
        if (coreBooking.getTariff().getMarket().compareTo(Market.DOMESTIC) <= 0) {
            shape = 1.151;
            scale = 1 / 0.046;
        } else {
            shape = 0.989;
            scale = 1 / 0.014;
        }
        for (final Service service:offeredServices){
            if (getRandom().nextDouble()<= serviceProbability){
                final int daysBeforeDeparture = (int) getRandom().getCutOffGammaDistributedDouble(
                        0,
                        coreBooking.getDaysBeforeDeparture(),
                        0,
                        shape,
                        scale);
                serviceOrders.add(
                        new ServiceOrder(
                                service,
                                1,
                                service.getPrice(coreBooking),
                                daysBeforeDeparture));
            }
        }

        return serviceOrders;

    }

    /**
     * Generate baggage orders based on the number of passengers. The time of
     * buying the bags in days before departure is pulled from a modified gamma
     * distribution.
     *
     * @param coreBooking
     *            the core booking
     * @return the list
     */
    private List<ServiceOrder> generateBaggageOrders(
            final CoreBooking coreBooking) {
        final Tariff tariff = coreBooking.getTariff();
        final HashMap<Service, Integer> chosenBags = chooseBags(
                tariff,
                coreBooking.getNumberPassengers());
        final List<ServiceOrder> serviceOrders = new ArrayList<>();
        final double shape;
        final double scale;
        if (tariff.getMarket().compareTo(Market.DOMESTIC) <= 0) {
            shape = 1.151;
            scale = 1 / 0.046;
        } else {
            shape = 0.989;
            scale = 1 / 0.014;
        }
        for (final Service baggageClass : chosenBags.keySet()) {
            serviceOrders.add(new ServiceOrder(baggageClass, chosenBags.get(baggageClass),
                    baggageClass.getPrice(
                            chosenBags.get(baggageClass),
                            coreBooking)
                    - baggageClass.getPrice(
                            tariff.getProduct().getNumberOfIncludedBagsByBaggageClass().get(
                                    baggageClass),
                            coreBooking),
                    (int) getRandom().getCutOffGammaDistributedDouble(
                            0,
                            coreBooking.getDaysBeforeDeparture(),
                            0,
                            shape,
                            scale)));
        }
        return serviceOrders;
    }

    /**
     * Selects seats for a booking.
     *
     * @param coreBooking
     *            the core booking
     * @return a selection of seats
     */
    private List<ServiceOrder> generateSeatOrders(
            final CoreBooking coreBooking) {
        final Tariff tariff = coreBooking.getTariff();
        final HashMap<Service, Integer> chosenSeats = chooseSeats(
                Service.getServicesByServiceClass(
                        tariff.getProduct().getServices(),
                        SeatGroup.class),
                coreBooking.getNumberPassengers());
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
                coreBooking.getDaysBeforeDeparture(),
                0,
                shape,
                scale);
        final List<ServiceOrder> orders = new ArrayList<>();
        for (final Service seatGroup: chosenSeats.keySet()){
            orders.add(new ServiceOrder(seatGroup,chosenSeats.get(seatGroup),
                    seatGroup.getPrice(chosenSeats.get(seatGroup), coreBooking),
                    daysBeforeDeparture));
        }
        return orders;
    }

    /**
     * Gets the number of bags for a booking. This number has to be greater than
     * the sum of bags that are already included in the product and scales with
     * the number of passengers.
     *
     * @param tariff
     *            the tariff
     * @param numberPassengers
     *            the number passengers
     * @return the number of bags
     */
    private int getNumberOfBags(final Tariff tariff,
            final int numberPassengers) {
        return Math.max(getRandom().nextInt(
                minimumNumberBags * numberPassengers,
                maximumNumberBags * numberPassengers + 1),
                tariff.getProduct().getNumberOfIncludedBagsByBaggageClass().values().stream().mapToInt(
                        Integer::intValue).sum() * numberPassengers);
    }

    /**
     * Gets the random number generator.
     *
     * @return the random
     */
    private ExtendedRandom getRandom() {
        return random;
    }

}

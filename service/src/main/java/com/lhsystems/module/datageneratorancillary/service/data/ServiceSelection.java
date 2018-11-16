package com.lhsystems.module.datageneratorancillary.service.data;

import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class ServiceSelection shows what services where chosen during a booking.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Table(name = "ServiceSelection")
public final class ServiceSelection {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The chosen services. */
    @ElementCollection
    @MapKeyJoinColumn(name = "SERVICE_ID", referencedColumnName = "id")
    @CollectionTable(name = "ChosenService")
    private final Map<Service, Integer> chosenService;

    /** The prices to which the services were booked. */
    @ElementCollection
    @MapKeyJoinColumn(name = "SERVICE_ID", referencedColumnName = "id")
    @CollectionTable(name = "Price")
    private final Map<Service, Double> prices;

    /** The days before departure on which the services were booked. */
    @ElementCollection
    @MapKeyJoinColumn(name = "SERVICE_ID", referencedColumnName = "id")
    @CollectionTable(name = "DaysBeforeDeparture")
    private final Map<Service, Integer> daysBeforeDeparture;

    /**
     * Instantiates a new service selection.
     *
     * @param paramChosenService
     *            the chosen services
     * @param paramPrices
     *            the prices to which the services were booked
     * @param paramDaysBeforeDeparture
     *            the days before departure for each booked service
     */
    public ServiceSelection(final Map<Service, Integer> paramChosenService,
            final Map<Service, Double> paramPrices,
            final Map<Service, Integer> paramDaysBeforeDeparture) {
        if (!paramChosenService.keySet().equals(paramPrices.keySet())){
            throw new RuntimeException("Keysets of chosen services and prices do not match");
        }
        if (!paramChosenService.keySet().equals(
                paramDaysBeforeDeparture.keySet())) {
            throw new RuntimeException(
                    "Keysets of chosen services and days before departure do not match");
        }
        chosenService = paramChosenService;
        prices = paramPrices;
        daysBeforeDeparture = paramDaysBeforeDeparture;
    }

    /**
     * Adds the entries of another service selection to this instance. As new
     * Days Before Departure we chose the latter of the two options
     *
     * @param otherSelection
     *            the other selection
     * @return the service selection
     */
    public ServiceSelection add(final ServiceSelection otherSelection) {
        for (final Service service : otherSelection.getServices()) {
            if (chosenService.containsKey(service)) {
                chosenService.put(
                        service,
                        chosenService.get(service)
                        + otherSelection.chosenService.get(service));
                prices.put(
                        service,
                        prices.get(service)
                        + otherSelection.prices.get(service));
                daysBeforeDeparture.put(
                        service,
                        Math.min(
                                daysBeforeDeparture.get(service),
                                otherSelection.daysBeforeDeparture.get(
                                        service)));
            } else {
                chosenService.put(
                        service,
                        otherSelection.chosenService.get(service));
                prices.put(service, otherSelection.prices.get(service));
                daysBeforeDeparture.put(
                        service,
                        otherSelection.daysBeforeDeparture.get(service));
            }
        }
        return this;
    }

    /**
     * Gets the services selected for now.
     *
     * @return the services
     */
    private Set<Service> getServices() {
        return chosenService.keySet();
    }

}

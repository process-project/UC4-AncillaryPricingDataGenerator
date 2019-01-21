package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Abstract class modelling all kinds of paid services and ancillaries.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The name of the service. */
    @Column(name = "NAME")
    private final String name;

    /**
     * The maximum number of times this ancillary can be sold for one flight.
     */
    @Column(name = "MAXIMUM_CAPACITY")
    private final int maximumCapacity;

    /**
     * Instantiates a new service.
     */
    public Service() {
        name = null;
        maximumCapacity = 0;
    }

    /**
     * Instantiates a new service.
     *
     * @param paramName
     *            the name
     * @param paramMaximumCapacity
     *            the maximum capacity
     */
    public Service(final String paramName, final int paramMaximumCapacity) {
        name = paramName;
        maximumCapacity = paramMaximumCapacity;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public final String getName(){
        return name;
    }

    /**
     * Gets the maximum capacity.
     *
     * @return the maximum capacity
     */
    public final int getMaximumCapacity(){
        return maximumCapacity;
    }

    /**
     * Return a price depending on the number of times this service is booked.
     *
     * @param number
     *            the number of times this service is booked
     * @return the price
     */
    public abstract double getPrice(int number);

    /**
     * Gets the price of buying this service once.
     *
     * @return the price of buying this service once.
     */
    public final double getPrice() {
        return getPrice(1);
    }

    /**
     * Gets the services of the given class from a collection of services.
     *
     * @param services
     *            the services
     * @param serviceClass
     *            the service class
     * @return the services of desired type
     */
    public static List<Service> getServicesByServiceClass(
            final Collection<Service> services,
            final Class<? extends Service> serviceClass) {
        return services.stream().filter(
                e -> e.getClass().equals(serviceClass)).collect(
                        Collectors.toList());
    }

    public abstract ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(
            final ServiceSerializedEntity.ServiceSerializedEntityBuilder serviceSerializedEntityBuilder);
}


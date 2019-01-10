package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.Booking;
import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Customer;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.CoreBookingConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.CustomerConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.ServiceOrderConfiguration;
import com.lhsystems.module.datageneratorancillary.service.generator.core.CoreBookingGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.CustomerGenerator;
import com.lhsystems.module.datageneratorancillary.service.generator.core.ServiceOrderGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.BookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.CoreBookingRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.CustomerRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.ServiceOrderRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Starts generating booking entities and saves them into database.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@org.springframework.stereotype.Service
public final class BookingGeneratorStarter {

    /** Repository for storing complete bookings. */
    private final BookingRepository bookingRepository;

    /** Repository for core bookings. */
    private final CoreBookingRepository coreBookingRepository;

    /** Repository for customers. */
    private final CustomerRepository customerRepository;

    /** Repository for saving services. */
    private final ServiceOrderRepository serviceOrderRepository;

    /**
     * Instantiates a new booking generator starter.
     *
     * @param bookingRepositoryParam
     *            the booking repository
     * @param serviceOrderRepositoryParam
     *            the service repository
     * @param coreBookingRepositoryParam
     *            the core booking repository
     * @param customerRepositoryParam
     *            the customer repository
     */
    @Autowired
    public BookingGeneratorStarter(
            final BookingRepository bookingRepositoryParam,
            final ServiceOrderRepository serviceOrderRepositoryParam,
            final CoreBookingRepository coreBookingRepositoryParam,
            final CustomerRepository customerRepositoryParam) {
        coreBookingRepository = coreBookingRepositoryParam;
        bookingRepository = bookingRepositoryParam;
        serviceOrderRepository = serviceOrderRepositoryParam;
        customerRepository = customerRepositoryParam;
    }

    /**
     * Generates booking entities and save all associated entities.
     *
     * @param flights
     *            the flights to be used for generation
     * @param customerConfiguration
     *            the customer configuration
     * @param coreBookingConfiguration
     *            the core booking configuration
     * @param serviceOrderConfiguration
     *            the service order configuration
     * @return a list of complete bookings
     */
    public List<Booking> generateBookingEntities(
            final List<Flight> flights,
            final CustomerConfiguration customerConfiguration,
            final CoreBookingConfiguration coreBookingConfiguration,
            final ServiceOrderConfiguration serviceOrderConfiguration) {
        final CustomerGenerator customerGenerator = new CustomerGenerator(
                customerConfiguration);
        final List<Customer> customers = customerGenerator.generateList(
                customerConfiguration.getNumberCustomers());
        //customerRepository.save(customers);

        final CoreBookingGenerator coreBookingGenerator = new CoreBookingGenerator(
                flights,
                customers,
                coreBookingConfiguration);
        final ServiceOrderGenerator serviceOrderGenerator = new ServiceOrderGenerator(
                serviceOrderConfiguration);
        final List<CoreBooking> coreBookings = coreBookingGenerator.generateList(
                customers.size());
        final List<ServiceOrder> allServiceOrders = new ArrayList<>();
        final List<Booking> bookings = new ArrayList<>();

        for (final CoreBooking coreBooking : coreBookings) {
            final List<ServiceOrder> serviceOrders = serviceOrderGenerator.generateOrders(
                    coreBooking);
            allServiceOrders.addAll(serviceOrders);
            bookings.add(
                    new Booking(
                            coreBooking,
                            coreBooking.getCustomer(),
                            serviceOrders));
        }
      /*  coreBookingRepository.save(coreBookings);
        serviceOrderRepository.save(allServiceOrders);
        bookingRepository.save(bookings);*/
        return bookings;
    }

}

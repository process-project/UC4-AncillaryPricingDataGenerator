package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import com.lhsystems.module.datageneratorancillary.service.data.CoreBooking;
import com.lhsystems.module.datageneratorancillary.service.data.Customer;
import com.lhsystems.module.datageneratorancillary.service.data.Flight;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Route;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Serialized Booking Entities to store information on bookings.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class BookingSerializedEntity {

    /** The compartment identifier. */
    private char compartmentIdentifier;

    /** The compartment name. */
    private String compartmentName;

    /** The age of the customer booking the flight. */
    private int customerAge;

    /** The gender of the customer booking the flight. */
    private String customerGender;

    /** The satisfaction of the customer booking the flight. */
    private int customerSatisfaction;

    /** The travel type of the customer booking the flight. */
    private String customerTravelType;

    /** The core booking days before departure. */
    private int daysBeforeDeparture;

    /** The departure date of the booked flight. */
    private LocalDate departureDate;

    /** The departure time of the booked flight. */
    private LocalTime departureTime;

    /** The destination airport of the flight. */
    private String destinationAirport;

    /** The flight number of the flight. */
    private Long flightNumber;

    /** The id. */
    private UUID id;

    /** The market of the flight. */
    private String market;

    /** The number of passengers of booking. */
    private int numberPassengers;

    /** The origin airport of the flight. */
    private String originAirport;

    /** The product name. */
    private String productName;

    /** The ticket price. */
    private Double ticketPrice;

    /** The point of sale. */
    private String pointOfSale;

    /** The booking channel. */
    private String bookingChannel;

    /**
     * Instantiates a new booking serialized entity.
     */
    private BookingSerializedEntity (){
    }


    /**
     * Instantiates a new booking serialized entity.
     *
     * @param bookingSerializedEntityBuilder
     *            the booking serialized entity builder
     */
    BookingSerializedEntity(
            final BookingSerializedEntityBuilder bookingSerializedEntityBuilder) {
        id = UUID.randomUUID();
        daysBeforeDeparture = bookingSerializedEntityBuilder.daysBeforeDeparture;
        numberPassengers = bookingSerializedEntityBuilder.numberPassengers;
        ticketPrice = bookingSerializedEntityBuilder.ticketPrice;
        departureDate = bookingSerializedEntityBuilder.departureDate;
        departureTime = bookingSerializedEntityBuilder.departureTime;
        customerAge = bookingSerializedEntityBuilder.customerAge;
        customerGender = bookingSerializedEntityBuilder.customerGender;
        customerTravelType = bookingSerializedEntityBuilder.customerTravelType;
        customerSatisfaction = bookingSerializedEntityBuilder.customerSatisfaction;
        destinationAirport = bookingSerializedEntityBuilder.destinationAirport;
        originAirport = bookingSerializedEntityBuilder.originAirport;
        market = bookingSerializedEntityBuilder.market;
        flightNumber = bookingSerializedEntityBuilder.flightNumber;
        compartmentIdentifier = bookingSerializedEntityBuilder.compartmentIdentifier;
        compartmentName = bookingSerializedEntityBuilder.compartmentName;
        productName = bookingSerializedEntityBuilder.productName;
        pointOfSale = bookingSerializedEntityBuilder.pointOfSale;
        bookingChannel = bookingSerializedEntityBuilder.bookingChannel;
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the days before departure.
     *
     * @return the days before departure
     */
    public int getDaysBeforeDeparture() {
        return daysBeforeDeparture;
    }

    /**
     * Gets the number of passengers.
     *
     * @return the number of passengers
     */
    public int getNumberPassengers() {
        return numberPassengers;
    }

    /**
     * Gets the ticket price.
     *
     * @return the ticket price
     */
    public Double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Gets the departure date.
     *
     * @return the departure date
     */
    public String getDepartureDate() {
        return departureDate.toString();
    }

    /**
     * Gets the departure time.
     *
     * @return the departure time
     */
    public String getDepartureTime() {
        return departureTime.toString();
    }

    /**
     * Gets the customer age.
     *
     * @return the customer age
     */
    public int getCustomerAge() {
        return customerAge;
    }

    /**
     * Gets the customers gender.
     *
     * @return the customers gender
     */
    public String getCustomerGender() {
        return customerGender;
    }

    /**
     * Gets the customers travel type.
     *
     * @return the customers travel type
     */
    public String getCustomerTravelType() {
        return customerTravelType;
    }

    /**
     * Gets the customers satisfaction.
     *
     * @return the customers satisfaction
     */
    public int getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    /**
     * Gets the flights destination airport.
     *
     * @return the destination airport
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Gets the flights origin airport.
     *
     * @return the origin airport
     */
    public String getOriginAirport() {
        return originAirport;
    }

    /**
     * Gets the flights market.
     *
     * @return the flights market
     */
    public String getMarket() {
        return market;
    }

    /**
     * Gets the flights flight number.
     *
     * @return the flights flight number
     */
    public Long getFlightNumber() {
        return flightNumber;
    }

    /**
     * Gets the compartment identifier.
     *
     * @return the compartment identifier
     */
    public char getCompartmentIdentifier() {
        return compartmentIdentifier;
    }

    /**
     * Gets the compartment name.
     *
     * @return the compartment name
     */
    public String getCompartmentName() {
        return compartmentName;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Gets the point of sale.
     *
     * @return the point of sale
     */
    public String getPointOfSale() {
        return pointOfSale;
    }

    /**
     * Gets the booking channel.
     *
     * @return the booking channel
     */
    public String getBookingChannel() {
        return bookingChannel;
    }

    /**
     * Builds Serialized Entity Builder.
     */
    public static final class BookingSerializedEntityBuilder {

        /** The compartment identifier. */
        private char compartmentIdentifier;

        /** The compartment name. */
        private String compartmentName;

        /** The customer age. */
        private int customerAge;

        /** The customers gender. */
        private String customerGender;

        /** The customers satisfaction. */
        private int customerSatisfaction;

        /** The customers travel type. */
        private String customerTravelType;

        /** The days before departure. */
        private int daysBeforeDeparture;

        /** The departure date. */
        private LocalDate departureDate;

        /** The departure time. */
        private LocalTime departureTime;

        /** The destination airport. */
        private String destinationAirport;

        /** The flight number. */
        private Long flightNumber;

        /** The market. */
        private String market;

        /** The number passengers. */
        private int numberPassengers;

        /** The origin airport. */
        private String originAirport;

        /** The product name. */
        private String productName;

        /** The ticket price. */
        private Double ticketPrice;

        /** The point of sale. */
        private String pointOfSale;

        /** The booking channel. */
        private String bookingChannel;


        /**
         * Instantiates a new booking serialized entity builder. Default
         * constructor to satisfy checkstyle requirements.
         */
        public BookingSerializedEntityBuilder() {
        }

        /**
         * Sets the fields of the customers.
         *
         * @param customer
         *            the customer
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setCustomerEntities(final Customer customer) {
            customerAge = customer.getAge();
            customerGender = customer.getGender().name();
            customerSatisfaction = customer.getSatisfaction();
            customerTravelType = customer.getTravelType().name();
            return this;
        }

        /**
         * Sets the fields of the route of the flight.
         *
         * @param route
         *            the route
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setRouteFields(final Route route) {
            destinationAirport = route.getDestinationAirport().getIata();
            originAirport = route.getOriginAirport().getIata();
            return this;
        }

        /**
         * Sets the flight fields.
         *
         * @param flight
         *            the flight
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setFlightFields(final Flight flight) {
            departureDate = flight.getDepartureDate();
            departureTime = flight.getDepartureTime();
            flightNumber = flight.getFlightNumber();
            return this;
        }

        /**
         * Sets the tariff fields.
         *
         * @param tariff
         *            the tariff
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setTariffFields(final Tariff tariff) {
            market = tariff.getMarket().name();
            ticketPrice = tariff.getPrice();
            return this;
        }

        /**
         * Sets the core booking fields.
         *
         * @param coreBooking
         *            the core booking
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setCoreBookingFields(final CoreBooking coreBooking) {
            numberPassengers = coreBooking.getNumberPassengers();
            daysBeforeDeparture = coreBooking.getDaysBeforeDeparture();
            pointOfSale = coreBooking.getPointOfSale().name();
            bookingChannel = coreBooking.getBookingChannel().getChannelName();
            return this;
        }

        /**
         * Sets the product fields.
         *
         * @param product
         *            the product
         * @return the booking serialized entity builder
         */
        public BookingSerializedEntityBuilder setProductFields(final Product product) {
            compartmentIdentifier = product.getCompartment().getIdentifier();
            compartmentName = product.getCompartment().getName();
            productName = product.getName();
            return this;
        }

        /**
         * Builds a serialized booking entity.
         *
         * @return the booking serialized entity
         */
        public BookingSerializedEntity build() {
            return new BookingSerializedEntity(this);
        }
    }

}

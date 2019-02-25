package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import com.lhsystems.module.datageneratorancillary.service.data.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class BookingSerializedEntity {
    private UUID id;

    private int daysBeforeDeparture;
    private int numberPassengers;
    private Double ticketPrice;
    private LocalDate departureDate;
    private LocalTime departureTime;

    private int customerAge;
    private String customerGender;
    private String customerTravelType;
    private int customerSatisfaction;

    private String destinationAirport;
    private String originAirport;
    private String market;
    private int flightNumber;

    public UUID getId() {
        return id;
    }

    public int getDaysBeforeDeparture() {
        return daysBeforeDeparture;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public String getDepartureDate() {
        return departureDate.toString();
    }

    public String getDepartureTime() {
        return departureTime.toString();
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public String getCustomerTravelType() {
        return customerTravelType;
    }

    public int getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public String getMarket() {
        return market;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    private BookingSerializedEntity (){
    }

    private BookingSerializedEntity(final BookingSerializedEntityBuilder bookingSerializedEntityBuilder) {
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
    }


    public static class BookingSerializedEntityBuilder {
        private int daysBeforeDeparture;
        private int numberPassengers;
        private Double ticketPrice;
        private LocalDate departureDate;
        private LocalTime departureTime;

        private int customerAge;
        private String customerGender;
        private String customerTravelType;
        private int customerSatisfaction;

        private String destinationAirport;
        private String originAirport;
        private String market;
        private int flightNumber;

        public BookingSerializedEntityBuilder setCustomerEntities(final Customer customer) {
            this.customerAge = customer.getAge();
            this.customerGender = customer.getGender().name();
            this.customerSatisfaction = customer.getSatisfaction();
            this.customerTravelType = customer.getTravelType().name();
            return this;
        }

        public BookingSerializedEntityBuilder setRouteFields(final Route route) {
            this.destinationAirport = route.getDestinationAirport().getIata();
            this.originAirport = route.getOriginAirport().getIata();
            return this;
        }

        public BookingSerializedEntityBuilder setFlightFields(final Flight flight) {
            this.departureDate = flight.getDepartureDate();
            this.departureTime = flight.getDepartureTime();
            this.flightNumber = flight.getFlightNumber();
            return this;
        }

        public BookingSerializedEntityBuilder setTariffFields(final Tariff tariff) {
            this.market = tariff.getMarket().name();
            this.ticketPrice = tariff.getPrice();
            return this;
        }

        public BookingSerializedEntityBuilder setCoreBookingFields(final CoreBooking coreBooking) {
            this.numberPassengers = coreBooking.getNumberPassengers();
            this.daysBeforeDeparture = coreBooking.getDaysBeforeDeparture();
            return this;
        }

        public BookingSerializedEntity build() {
            return new BookingSerializedEntity(this);
        }
    }

}

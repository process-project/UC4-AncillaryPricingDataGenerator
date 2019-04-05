package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Donation;
import com.lhsystems.module.datageneratorancillary.service.data.EntertainmentOnBoard;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;

import java.util.UUID;

/**
 * Serialized Service Entities to store information on service bookings.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ServiceSerializedEntity {

    /** The booking id. */
    private UUID bookingId;

    /** The service id. */
    private UUID serviceId;


    /** The maximal baggage circumference. */
    private double baggageCircumferenceMax;

    /** The maximal baggage height. */
    private double baggageHeightMax;

    /** The maximal baggage length. */
    private double baggageLengthMax;

    /** The maximal baggage width. */
    private double baggageWidthMax;

    /** The price of an additional bag. */
    private double priceAdditionalBag;

    /** The price of a first bag. */
    private double priceFirstBag;

    /** The price of a second bag. */
    private double priceSecondBag;

    /** The maximal baggage count. */
    private int baggageCountMax;

    /** The maximal baggage weight. */
    private double baggageWeightMax;

    /** The number of seats. */
    private int numberSeats;

    /** The seat price. */
    private double seatPrice;

    /** The count. */
    private int count;

    /** The price. */
    private double price;

    /** The days before departure. */
    private int daysBeforeDeparture;

    /** The deviation price. */
    private double deviationPrice;

    /** The entertainment on board price. */
    private double entertainmentOnBoardPrice;

    /**
     * Instantiates a new service serialized entity.
     */
    private ServiceSerializedEntity (){ }


    /**
     * Instantiates a new service serialized entity.
     *
     * @param serviceSerializedEntityBuilder
     *            the service serialized entity builder
     */
    public ServiceSerializedEntity(final ServiceSerializedEntityBuilder serviceSerializedEntityBuilder) {
        serviceId = UUID.randomUUID();
        bookingId = serviceSerializedEntityBuilder.bookingId;
        baggageCircumferenceMax = serviceSerializedEntityBuilder.baggageCircumferenceMax;
        baggageHeightMax = serviceSerializedEntityBuilder.baggageHeightMax;
        baggageLengthMax = serviceSerializedEntityBuilder.baggageLengthMax;
        baggageWidthMax = serviceSerializedEntityBuilder.baggageWidthMax;
        priceAdditionalBag = serviceSerializedEntityBuilder.priceAdditionalBag;
        priceFirstBag = serviceSerializedEntityBuilder.priceFirstBag;
        priceSecondBag = serviceSerializedEntityBuilder.priceSecondBag;
        baggageCountMax = serviceSerializedEntityBuilder.baggageCountMax;
        baggageWeightMax = serviceSerializedEntityBuilder.baggageWeightMax;
        numberSeats = serviceSerializedEntityBuilder.numberSeats;
        seatPrice = serviceSerializedEntityBuilder.seatPrice;
        daysBeforeDeparture = serviceSerializedEntityBuilder.daysBeforeDeparture;
        price = serviceSerializedEntityBuilder.price;
        count = serviceSerializedEntityBuilder.count;
        deviationPrice = serviceSerializedEntityBuilder.deviationPrice;
        entertainmentOnBoardPrice = serviceSerializedEntityBuilder.entertainmentOnBoardPrice;
    }


    /**
     * Gets the booking id.
     *
     * @return the booking id
     */
    public UUID getBookingId() {
        return bookingId;
    }

    /**
     * Gets the service id.
     *
     * @return the service id
     */
    public UUID getServiceId() {
        return serviceId;
    }

    /**
     * Gets the baggage circumference max.
     *
     * @return the baggage circumference max
     */
    public double getBaggageCircumferenceMax() {
        return baggageCircumferenceMax;
    }

    /**
     * Gets the baggage height max.
     *
     * @return the baggage height max
     */
    public double getBaggageHeightMax() {
        return baggageHeightMax;
    }

    /**
     * Gets the baggage length max.
     *
     * @return the baggage length max
     */
    public double getBaggageLengthMax() {
        return baggageLengthMax;
    }

    /**
     * Gets the baggage width max.
     *
     * @return the baggage width max
     */
    public double getBaggageWidthMax() {
        return baggageWidthMax;
    }

    /**
     * Gets the price additional bag.
     *
     * @return the price additional bag
     */
    public double getPriceAdditionalBag() {
        return priceAdditionalBag;
    }

    /**
     * Gets the price first bag.
     *
     * @return the price first bag
     */
    public double getPriceFirstBag() {
        return priceFirstBag;
    }

    /**
     * Gets the price second bag.
     *
     * @return the price second bag
     */
    public double getPriceSecondBag() {
        return priceSecondBag;
    }

    /**
     * Gets the baggage count max.
     *
     * @return the baggage count max
     */
    public int getBaggageCountMax() {
        return baggageCountMax;
    }

    /**
     * Gets the baggage weight max.
     *
     * @return the baggage weight max
     */
    public double getBaggageWeightMax() {
        return baggageWeightMax;
    }

    /**
     * Gets the number seats.
     *
     * @return the number seats
     */
    public int getNumberSeats() {
        return numberSeats;
    }

    /**
     * Gets the seat price.
     *
     * @return the seat price
     */
    public double getSeatPrice() {
        return seatPrice;
    }

    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
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
     * Gets the deviation price.
     *
     * @return the deviation price
     */
    public double getDeviationPrice() {
        return deviationPrice;
    }

    /**
     * Gets the entertainment on board price.
     *
     * @return the entertainment on board price
     */
    public double getEntertainmentOnBoardPrice() {
        return entertainmentOnBoardPrice;
    }


    /**
     * The Class ServiceSerializedEntityBuilder.
     */
    public static final class ServiceSerializedEntityBuilder {

        /** The booking id. */
        private final UUID bookingId;

        /** The baggage circumference max. */
        private double baggageCircumferenceMax;

        /** The baggage height max. */
        private double baggageHeightMax;

        /** The baggage length max. */
        private double baggageLengthMax;

        /** The baggage width max. */
        private double baggageWidthMax;

        /** The price additional bag. */
        private double priceAdditionalBag;

        /** The price first bag. */
        private double priceFirstBag;

        /** The price second bag. */
        private double priceSecondBag;

        /** The baggage count max. */
        private int baggageCountMax;

        /** The baggage weight max. */
        private double baggageWeightMax;

        /** The number seats. */
        private int numberSeats;

        /** The seat price. */
        private double seatPrice;

        /** The count. */
        private int count;

        /** The price. */
        private double price;

        /** The days before departure. */
        private int daysBeforeDeparture;

        /** The deviation price. */
        private double deviationPrice;

        /** The entertainment on board price. */
        private double entertainmentOnBoardPrice;

        /**
         * Instantiates a new service serialized entity builder.
         *
         * @param bookingIdParam
         *            the booking id param
         */
        public ServiceSerializedEntityBuilder(final UUID bookingIdParam) {
            bookingId = bookingIdParam;
        }

        /**
         * Sets the baggage fields.
         *
         * @param baggageClass
         *            the baggage class
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setBaggageFields(final BaggageClass baggageClass) {
            baggageCircumferenceMax = baggageClass.getBaggageLimits().getBaggageSize().getCircumferenceMax();
            baggageHeightMax = baggageClass.getBaggageLimits().getBaggageSize().getHeightMax();
            baggageLengthMax = baggageClass.getBaggageLimits().getBaggageSize().getLengthMax();
            baggageWidthMax = baggageClass.getBaggageLimits().getBaggageSize().getWidthMax();
            priceAdditionalBag = baggageClass.getBaggagePricing().getAdditionalPrice();
            priceFirstBag = baggageClass.getBaggagePricing().getFirstPrice();
            priceSecondBag = baggageClass.getBaggagePricing().getSecondPrice();
            return this;
        }

        /**
         * Sets the seat fields.
         *
         * @param seatGroup
         *            the seat group
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setSeatFields(final SeatGroup seatGroup) {
            seatPrice = seatGroup.getSeatPrice();
            numberSeats = seatGroup.getNumberSeats();
            return this;
        }

        /**
         * Sets the service order fields.
         *
         * @param serviceOrder
         *            the service order
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setServiceOrderFields(final ServiceOrder serviceOrder) {
            count = serviceOrder.getCount();
            price = serviceOrder.getPrice();
            daysBeforeDeparture = serviceOrder.getDaysBeforeDeparture();
            return this;
        }

        /**
         * Sets the donation fields.
         *
         * @param donation
         *            the donation
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setDonationFields(final Donation donation) {
            deviationPrice = donation.getPrice(0, null);
            return this;
        }

        /**
         * Sets the entertainments fields.
         *
         * @param entertainment
         *            the entertainment
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setEntertainmentsFields(final EntertainmentOnBoard entertainment) {
            entertainmentOnBoardPrice = entertainment.getPrice(0, null);
            return this;
        }

        /**
         * Sets the service fields.
         *
         * @param service
         *            the service
         * @return the service serialized entity builder
         */
        public ServiceSerializedEntityBuilder setServiceFields(final Service service) {
            service.populateServiceBuilder(this);
            return this;
        }

        /**
         * Builds the.
         *
         * @return the service serialized entity
         */
        public ServiceSerializedEntity build() {
            return new ServiceSerializedEntity(this);
        }
    }
}

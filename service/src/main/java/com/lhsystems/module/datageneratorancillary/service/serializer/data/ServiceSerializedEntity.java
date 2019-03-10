package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Donation;
import com.lhsystems.module.datageneratorancillary.service.data.EntertainmentOnBoard;
import com.lhsystems.module.datageneratorancillary.service.data.SeatGroup;
import com.lhsystems.module.datageneratorancillary.service.data.Service;
import com.lhsystems.module.datageneratorancillary.service.data.ServiceOrder;
import java.util.UUID;

public class ServiceSerializedEntity {
    private UUID bookingId;
    private UUID serviceId;

    private double baggageCircumferenceMax;
    private double baggageHeightMax;
    private double baggageLengthMax;
    private double baggageWidthMax;
    private double priceAdditionalBag;
    private double priceFirstBag;
    private double priceSecondBag;
    private int baggageCountMax;
    private double baggageWeightMax;

    private int numberSeats;
    private double seatPrice;

    private int count;
    private double price;
    private int daysBeforeDeparture;

    private double deviationPrice;
    private double entertainmentOnBoardPrice;

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public double getBaggageCircumferenceMax() {
        return baggageCircumferenceMax;
    }

    public double getBaggageHeightMax() {
        return baggageHeightMax;
    }

    public double getBaggageLengthMax() {
        return baggageLengthMax;
    }

    public double getBaggageWidthMax() {
        return baggageWidthMax;
    }

    public double getPriceAdditionalBag() {
        return priceAdditionalBag;
    }

    public double getPriceFirstBag() {
        return priceFirstBag;
    }

    public double getPriceSecondBag() {
        return priceSecondBag;
    }

    public int getBaggageCountMax() {
        return baggageCountMax;
    }

    public double getBaggageWeightMax() {
        return baggageWeightMax;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public int getDaysBeforeDeparture() {
        return daysBeforeDeparture;
    }

    public double getDeviationPrice() {
        return deviationPrice;
    }

    public double getEntertainmentOnBoardPrice() {
        return entertainmentOnBoardPrice;
    }

    private ServiceSerializedEntity (){ }


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
    };

    public static class ServiceSerializedEntityBuilder {
        private UUID bookingId;
        private double baggageCircumferenceMax;
        private double baggageHeightMax;
        private double baggageLengthMax;
        private double baggageWidthMax;
        private double priceAdditionalBag;
        private double priceFirstBag;
        private double priceSecondBag;
        private int baggageCountMax;
        private double baggageWeightMax;

        private int numberSeats;
        private double seatPrice;

        private int count;
        private double price;
        private int daysBeforeDeparture;

        private double deviationPrice;
        private double entertainmentOnBoardPrice;


        public ServiceSerializedEntityBuilder(final UUID bookingIdParam) {
            this.bookingId = bookingIdParam;
        }

        public ServiceSerializedEntityBuilder setBaggageFields(final BaggageClass baggageClass) {
            this.baggageCircumferenceMax = baggageClass.getBaggageLimits().getBaggageSize().getCircumferenceMax();
            this.baggageHeightMax = baggageClass.getBaggageLimits().getBaggageSize().getHeightMax();
            this.baggageLengthMax = baggageClass.getBaggageLimits().getBaggageSize().getLengthMax();
            this.baggageWidthMax = baggageClass.getBaggageLimits().getBaggageSize().getWidthMax();
            this.priceAdditionalBag = baggageClass.getBaggagePricing().getAdditionalPrice();
            this.priceFirstBag = baggageClass.getBaggagePricing().getFirstPrice();
            this.priceSecondBag = baggageClass.getBaggagePricing().getSecondPrice();
            return this;
        }

        public ServiceSerializedEntityBuilder setSeatFields(final SeatGroup seatGroup) {
            this.seatPrice = seatGroup.getSeatPrice();
            this.numberSeats = seatGroup.getNumberSeats();
            return this;
        }

        public ServiceSerializedEntityBuilder setServiceOrderFields(final ServiceOrder serviceOrder) {
            this.count = serviceOrder.getCount();
            this.price = serviceOrder.getPrice();
            this.daysBeforeDeparture = serviceOrder.getDaysBeforeDeparture();
            return this;
        }

        public ServiceSerializedEntityBuilder setDonationFields(final Donation donation) {
            this.deviationPrice = donation.getPrice(0, null);
            return this;
        }

        public ServiceSerializedEntityBuilder setEntertainmentsFields(final EntertainmentOnBoard entertainment) {
            this.entertainmentOnBoardPrice = entertainment.getPrice(0, null);
            return this;
        }

        public ServiceSerializedEntityBuilder setServiceFields(final Service service) {
            service.populateServiceBuilder(this);
            return this;
        }

        public ServiceSerializedEntity build() {
            return new ServiceSerializedEntity(this);
        }
    }
}

package com.lhsystems.module.datageneratorancillary.service.serializer.data;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
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


        public ServiceSerializedEntityBuilder(final UUID bookingIdParam) {
            this.bookingId = bookingIdParam;
        }

        public ServiceSerializedEntityBuilder setBaggageFields(final BaggageClass baggageClass) {
            this.baggageCircumferenceMax = baggageClass.getBaggageLimits().getBaggageSize().getCircumferenceMax();
            this.baggageHeightMax = baggageClass.getBaggageLimits().getBaggageSize().getHeightMax();
            this.baggageLengthMax = baggageClass.getBaggageLimits().getBaggageSize().getLengthMax();
            this.baggageWidthMax = baggageClass.getBaggageLimits().getBaggageSize().getWidthMax();
            this.priceAdditionalBag = baggageClass.getBaggagePricing().getAdditionalPrice();
            this.priceFirstBag = baggageClass.getBaggagePricing().getAdditionalPrice();
            this.priceSecondBag = baggageClass.getBaggagePricing().getFirstPrice();
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

        public ServiceSerializedEntityBuilder setServiceFields(final Service service) {
            service.populateServiceBuilder(this);
            return this;
        }

        public ServiceSerializedEntity build() {
            return new ServiceSerializedEntity(this);
        }
    }
}

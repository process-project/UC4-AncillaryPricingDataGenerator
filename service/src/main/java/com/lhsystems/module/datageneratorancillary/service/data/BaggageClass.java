package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * data structure representing a baggage class in an ancillary model.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class BaggageClass extends Service {

    /** The baggage limits. */
    @OneToOne
    @JoinColumn(name = "BAGGAGE_LIMITS")
    private final BaggageLimits baggageLimits;

    /** The baggage pricing. */
    @OneToOne
    @JoinColumn(name = "BAGGAGE_PRICING")
    private final BaggagePricing baggagePricing;

    /**
     * Default Constructor needed for an Entity. Instantiates a new baggage
     * class.
     */
    public BaggageClass() {
        super();
        baggagePricing = null;
        baggageLimits = null;
    }

    /**
     * Instantiates a new baggage class.
     *
     * @param paramName
     *            the name
     * @param paramMaximumCapacity
     *            the maximum capacity
     * @param paramBaggageLimits
     *            the baggage limits
     * @param paramBaggagePricing
     *            the baggage pricing
     */
    public BaggageClass(final String paramName, final int paramMaximumCapacity,
            final BaggageLimits paramBaggageLimits,
            final BaggagePricing paramBaggagePricing) {
        super(paramName, paramMaximumCapacity);
        baggageLimits = paramBaggageLimits;
        baggagePricing = paramBaggagePricing;
    }

    /**
     * Gets the baggage limits.
     *
     * @return the baggage limits
     */
    public BaggageLimits getBaggageLimits() {
        return baggageLimits;
    }

    /**
     * returns the baggage pricing.
     *
     * @return the baggage pricing
     */
    public BaggagePricing getBaggagePricing() {
        return baggagePricing;
    }

    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        int price = 0;
        if (number > 0) {
            price += baggagePricing.getFirstPrice();
        }
        if (number > 1) {
            price += baggagePricing.getSecondPrice();
        }
        if (number >2) {
            price += baggagePricing.getAdditionalPrice() * (number - 2);
        }
        return price;
    }

    @Override
    public final ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(
            final ServiceSerializedEntity.ServiceSerializedEntityBuilder serviceSerializedEntityBuilder) {
        return serviceSerializedEntityBuilder.setBaggageFields(this);
    }
}
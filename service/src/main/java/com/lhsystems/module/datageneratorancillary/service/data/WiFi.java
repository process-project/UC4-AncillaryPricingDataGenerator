package com.lhsystems.module.datageneratorancillary.service.data;

import com.lhsystems.module.datageneratorancillary.service.serializer.data.ServiceSerializedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Data structure modeling on board wifi as a service with a tiered pricing
 * based on the market of the flight and unlimited capacity.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
@Entity
public final class WiFi extends Service {

    /** The price of wifi on intercontinental flights. */
    @Column
    private final double priceIntercontinental;

    /** The price of wifi on continental or domestic flights. */
    @Column
    private final double priceShortRange;

    /**
     * Instantiates a new wifi Obbject.
     *
     * @param paramWiFiPriceIntercontinental
     *            the wifi price on intercontinental flights
     * @param paramWiFiPriceShortRange
     *            the wifi price on short range flights
     */
    public WiFi(final double paramWiFiPriceIntercontinental,
            final double paramWiFiPriceShortRange) {
        super("WiFi", Integer.MAX_VALUE);
        priceIntercontinental = paramWiFiPriceIntercontinental;
        priceShortRange = paramWiFiPriceShortRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final int number, final CoreBooking coreBooking) {
        if (coreBooking.getTariff().getMarket() == Market.INTERCONTINENTAL) {
            return priceIntercontinental;
        } else {
            return priceShortRange;
        }
    }

    @Override
    public ServiceSerializedEntity.ServiceSerializedEntityBuilder populateServiceBuilder(final ServiceSerializedEntity.ServiceSerializedEntityBuilder
                                                                                                     serviceSerializedEntityBuilder) {
        return serviceSerializedEntityBuilder;
    }

}

package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration responsible for service generation.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class ServiceConfiguration {

    /** The mean donation value. */
    private double donationMean;

    /** The donation name. */
    private String donationName;

    /** The standard deviation of a donation. */
    private double donationStandardDeviation;

    /** The mean entertainment on board price . */
    private double entertainmentOnBoardPriceMean;

    /** The capacity of the flowers service. */
    private int flowersCapacity;

    /** The fixed price of flowers. */
    private double flowersFixedPrice;

    /** The name of the flowers service. */
    private String flowersName;

    /** The mean price of a the hotel service. */
    private double hotelMeanPrice;

    /** The hotel name. */
    private String hotelName;

    /** The capacity of the parking service. */
    private int parkingCapacity;

    /** The parking fixed price. */
    private double parkingFixedPrice;

    /** The intercontinental WiFi price. */
    private double wiFiPriceIntercontinental;

    /** The WiFi price for domestic and continental tariffs. */
    private double wiFiPriceShortRange;

    /**
     * Instantiates a new service configuration.
     */
    public ServiceConfiguration() {
    }

    /**
     * Gets the mean donation.
     *
     * @return the donation mean
     */
    public double getDonationMean() {
        return donationMean;
    }

    /**
     * Gets the donation name.
     *
     * @return the donation name
     */
    public String getDonationName() {
        return donationName;
    }

    /**
     * Gets the donation standard deviation.
     *
     * @return the donation standard deviation
     */
    public double getDonationStandardDeviation() {
        return donationStandardDeviation;
    }

    /**
     * Gets the entertainment on board price mean.
     *
     * @return the entertainment on board price mean
     */
    public double getEntertainmentOnBoardPriceMean() {
        return entertainmentOnBoardPriceMean;
    }

    /**
     * Gets the flowers capacity.
     *
     * @return the flowers capacity
     */
    public int getFlowersCapacity() {
        return flowersCapacity;
    }

    /**
     * Gets the flowers fixed price.
     *
     * @return the flowers fixed price
     */
    public double getFlowersFixedPrice() {
        return flowersFixedPrice;
    }

    /**
     * Gets the flowers name.
     *
     * @return the flowers name
     */
    public String getFlowersName() {
        return flowersName;
    }

    /**
     * Gets the hotel mean price.
     *
     * @return the hotel mean price
     */
    public double getHotelMeanPrice() {
        return hotelMeanPrice;
    }

    /**
     * Gets the hotel name.
     *
     * @return the hotel name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Gets the parking capacity.
     *
     * @return the parking capacity
     */
    public int getParkingCapacity() {
        return parkingCapacity;
    }

    /**
     * Gets the parking fixed price.
     *
     * @return the parking fixed price
     */
    public double getParkingFixedPrice() {
        return parkingFixedPrice;
    }

    /**
     * Gets the wi fi price intercontinental.
     *
     * @return the wi fi price intercontinental
     */
    public double getWiFiPriceIntercontinental() {
        return wiFiPriceIntercontinental;
    }

    /**
     * Gets the wi fi price short range.
     *
     * @return the wi fi price short range
     */
    public double getWiFiPriceShortRange() {
        return wiFiPriceShortRange;
    }

    /**
     * Sets the donation mean.
     *
     * @param paramDonationMean
     *            the new donation mean
     */
    public void setDonationMean(final double paramDonationMean) {
        donationMean = paramDonationMean;
    }

    /**
     * Sets the donation name.
     *
     * @param paramDonationName
     *            the new donation name
     */
    public void setDonationName(final String paramDonationName) {
        donationName = paramDonationName;
    }

    /**
     * Sets the donation standard deviation.
     *
     * @param paramDonationStandardDeviation
     *            the new donation standard deviation
     */
    public void setDonationStandardDeviation(
            final double paramDonationStandardDeviation) {
        donationStandardDeviation = paramDonationStandardDeviation;
    }

    /**
     * Sets the entertainment on board price mean.
     *
     * @param paramEntertainmentOnBoardPriceMean
     *            the new entertainment on board price mean
     */
    public void setEntertainmentOnBoardPriceMean(
            final double paramEntertainmentOnBoardPriceMean) {
        entertainmentOnBoardPriceMean = paramEntertainmentOnBoardPriceMean;
    }

    /**
     * Sets the flowers capacity.
     *
     * @param paramFlowersCapacity
     *            the new flowers capacity
     */
    public void setFlowersCapacity(final int paramFlowersCapacity) {
        flowersCapacity = paramFlowersCapacity;
    }

    /**
     * Sets the flowers fixed price.
     *
     * @param paramFlowersFixedPrice
     *            the new flowers fixed price
     */
    public void setFlowersFixedPrice(final double paramFlowersFixedPrice) {
        flowersFixedPrice = paramFlowersFixedPrice;
    }

    /**
     * Sets the flowers name.
     *
     * @param paramFlowersName
     *            the new flowers name
     */
    public void setFlowersName(final String paramFlowersName) {
        flowersName = paramFlowersName;
    }

    /**
     * Sets the hotel mean price.
     *
     * @param paramHotelMeanPrice
     *            the new hotel mean price
     */
    public void setHotelMeanPrice(final double paramHotelMeanPrice) {
        hotelMeanPrice = paramHotelMeanPrice;
    }

    /**
     * Sets the hotel name.
     *
     * @param paramHotelName
     *            the new hotel name
     */
    public void setHotelName(final String paramHotelName) {
        hotelName = paramHotelName;
    }

    /**
     * Sets the parking capacity.
     *
     * @param paramParkingCapacity
     *            the new parking capacity
     */
    public void setParkingCapacity(final int paramParkingCapacity) {
        parkingCapacity = paramParkingCapacity;
    }

    /**
     * Sets the parking fixed price.
     *
     * @param paramParkingFixedPrice
     *            the new parking fixed price
     */
    public void setParkingFixedPrice(final double paramParkingFixedPrice) {
        parkingFixedPrice = paramParkingFixedPrice;
    }

    /**
     * Sets the wi fi price intercontinental.
     *
     * @param paramWiFiPriceIntercontinental
     *            the new wi fi price intercontinental
     */
    public void setWiFiPriceIntercontinental(
            final double paramWiFiPriceIntercontinental) {
        wiFiPriceIntercontinental = paramWiFiPriceIntercontinental;
    }

    /**
     * Sets the wi fi price short range.
     *
     * @param paramWiFiPriceShortRange
     *            the new wi fi price short range
     */
    public void setWiFiPriceShortRange(final double paramWiFiPriceShortRange) {
        wiFiPriceShortRange = paramWiFiPriceShortRange;
    }
}

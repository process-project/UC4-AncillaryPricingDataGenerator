package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration for all generators.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class GeneratorConfiguration {

    /** Configuration for a baggage class generator. */
    private BaggageClassConfiguration baggageClassConfiguration;

    /** Configuration for a baggage limits generator. */
    private BaggageLimitsConfiguration baggageLimitsConfiguration;

    /** Configuration for a baggage pricing generator. */
    private BaggagePricingConfiguration baggagePricingConfiguration;

    /** Configuration for a baggage size generator. */
    private BaggageSizeConfiguration baggageSizeConfiguration;

    /** Configuration for a core booking generator. */
    private CoreBookingConfiguration coreBookingConfiguration;

    /** Configuration for a customer Generator. */
    private CustomerConfiguration customerConfiguration;

    /** Configuration for a flight generator. */
    private FlightConfiguration flightConfiguration;

    /** Configuration for a product generator. */
    private ProductConfiguration productConfiguration;

    /** Configuration for a seat group generator. */
    private SeatGroupConfiguration seatGroupConfiguration;

    /** Configuration for a service generator. */
    private ServiceConfiguration serviceConfiguration;

    /** Configuration for a service order generator. */
    private ServiceOrderConfiguration serviceOrderConfiguration;

    /** Configuration for a tariff generator. */
    private TariffConfiguration tariffConfiguration;

    /**
     * Instantiates a new generator configuration object.
     */
    public GeneratorConfiguration() {
    }


    /**
     * Gets the baggage class configuration.
     *
     * @return the baggage class configuration
     */
    public BaggageClassConfiguration getBaggageClassConfiguration() {
        return baggageClassConfiguration;
    }

    /**
     * Gets the baggage limits configuration.
     *
     * @return the baggage limits configuration
     */
    public BaggageLimitsConfiguration getBaggageLimitsConfiguration() {
        return baggageLimitsConfiguration;
    }


    /**
     * Gets the baggage pricing configuration.
     *
     * @return the baggage pricing configuration
     */
    public BaggagePricingConfiguration getBaggagePricingConfiguration() {
        return baggagePricingConfiguration;
    }


    /**
     * Gets the baggage size configuration.
     *
     * @return the baggage size configuration
     */
    public BaggageSizeConfiguration getBaggageSizeConfiguration() {
        return baggageSizeConfiguration;
    }


    /**
     * Gets the core booking configuration.
     *
     * @return the core booking configuration
     */
    public CoreBookingConfiguration getCoreBookingConfiguration() {
        return coreBookingConfiguration;
    }


    /**
     * Gets the customer configuration.
     *
     * @return the customer configuration
     */
    public CustomerConfiguration getCustomerConfiguration() {
        return customerConfiguration;
    }

    /**
     * Gets the product configuration.
     *
     * @return the product configuration
     */
    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }


    /**
     * Gets the seat group configuration.
     *
     * @return the seat group configuration
     */
    public SeatGroupConfiguration getSeatGroupConfiguration() {
        return seatGroupConfiguration;
    }


    /**
     * Gets the service generator configuration.
     *
     * @return the service configuration
     */
    public ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }


    /**

     * Gets the service order configuration.
     *
     * @return the service order configuration
     */
    public ServiceOrderConfiguration getServiceOrderConfiguration() {
        return serviceOrderConfiguration;
    }


    /**
     * Gets the tariff configuration.
     *
     * @return the tariff configuration
     */
    public TariffConfiguration getTariffConfiguration() {
        return tariffConfiguration;
    }


    /**
     * Gets the flight generator configuration.
     *
     * @return flight generator configuration
     */
    public FlightConfiguration getFlightConfiguration() {
        return flightConfiguration;
    }


    /**
     * Sets the baggage class configuration.
     *
     * @param paramBaggageClassConfiguration
     *            the new baggage class configuration
     */
    public void setBaggageClassConfiguration(
            final BaggageClassConfiguration paramBaggageClassConfiguration) {
        baggageClassConfiguration = paramBaggageClassConfiguration;
    }

    /**
     * Sets the baggage limits configuration.
     *
     * @param paramBaggageLimitsConfiguration
     *            the new baggage limits configuration
     */
    public void setBaggageLimitsConfiguration(
            final BaggageLimitsConfiguration paramBaggageLimitsConfiguration) {
        baggageLimitsConfiguration = paramBaggageLimitsConfiguration;
    }

    /**
     * Sets the baggage pricing configuration.
     *
     * @param paramBaggagePricingConfiguration
     *            the new baggage pricing configuration
     */
    public void setBaggagePricingConfiguration(
            final BaggagePricingConfiguration paramBaggagePricingConfiguration) {
        baggagePricingConfiguration = paramBaggagePricingConfiguration;
    }

    /**
     * Sets the baggage size configuration.
     *
     * @param paramBaggageSizeConfiguration
     *            the new baggage size configuration
     */
    public void setBaggageSizeConfiguration(
            final BaggageSizeConfiguration paramBaggageSizeConfiguration) {
        baggageSizeConfiguration = paramBaggageSizeConfiguration;
    }

    /**
     * Sets the core booking configuration.
     *
     * @param paramCoreBookingConfiguration
     *            the new core booking configuration
     */
    public void setCoreBookingConfiguration(
            final CoreBookingConfiguration paramCoreBookingConfiguration) {
        coreBookingConfiguration = paramCoreBookingConfiguration;
    }

    /**
     * Sets the customer configuration.
     *
     * @param paramCustomerConfiguration
     *            the new customer configuration
     */
    public void setCustomerConfiguration(
            final CustomerConfiguration paramCustomerConfiguration) {
        customerConfiguration = paramCustomerConfiguration;
    }

    /**
     * Sets the product configuration.
     *
     * @param paramProductConfiguration
     *            the new product configuration
     */
    public void setProductConfiguration(
            final ProductConfiguration paramProductConfiguration) {
        productConfiguration = paramProductConfiguration;
    }

    /**
     * Sets the seat group configuration.
     *
     * @param paramSeatGroupConfiguration
     *            the new seat group configuration
     */
    public void setSeatGroupConfiguration(
            final SeatGroupConfiguration paramSeatGroupConfiguration) {
        seatGroupConfiguration = paramSeatGroupConfiguration;
    }

    /**
     * Sets the service configuration.
     *
     * @param paramServiceConfiguration
     *            the new service configuration
     */
    public void setServiceConfiguration(
            final ServiceConfiguration paramServiceConfiguration) {
        serviceConfiguration = paramServiceConfiguration;
    }


    /**

     * Sets the service order configuration.
     *
     * @param paramServiceOrderConfiguration
     *            the new service order configuration
     */
    public void setServiceOrderConfiguration(
            final ServiceOrderConfiguration paramServiceOrderConfiguration) {
        serviceOrderConfiguration = paramServiceOrderConfiguration;
    }


    /**
     * Sets the tariff configuration.
     *
     * @param paramTariffConfiguration
     *            the new tariff configuration
     */
    public void setTariffConfiguration(
            final TariffConfiguration paramTariffConfiguration) {
        tariffConfiguration = paramTariffConfiguration;
    }

    /**
     * Set flight configuration, used for reading yml file.
     *
     * @param flightParam
     *            flight configuration from zml file
     */
    public void setFlightConfiguration(
            final FlightConfiguration flightParam) {
        flightConfiguration = flightParam;
    }


}

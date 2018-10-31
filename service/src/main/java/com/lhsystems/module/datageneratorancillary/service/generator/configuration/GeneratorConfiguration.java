package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration for all generators.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class GeneratorConfiguration {

    /** Configuration for a flight generator. */
    private FlightGeneratorConfiguration flightConfiguration;

    /** Configuration for a baggage generator. */
    private BaggageGeneratorConfiguration baggageConfiguration;

    /** Configuration for a seat generator. */
    private SeatConfiguration seatConfiguration;

    /** Number of products that should be generated. */
    private int numberProducts;

    /** Number of tariffs that should be generated. */
    private int numberTariffs;

    /** Number of bookings that should be generated. */
    private int numberBookings;

    /**
     * Instantiates a new generator configuration object.
     */
    public GeneratorConfiguration() {
    }

    /**
     * Gets the baggage generator configuration.
     *
     * @return baggage generator configuration
     */
    public BaggageGeneratorConfiguration getBaggageConfiguration() {
        return baggageConfiguration;
    }

    /**
     * Gets the number of bookings.
     *
     * @return the number bookings
     */
    public int getNumberBookings() {
        return numberBookings;
    }

    /**
     * Gets the flight generator configuration.
     *
     * @return flight generator configuration
     */
    public FlightGeneratorConfiguration getFlightConfiguration() {
        return flightConfiguration;
    }

    /**
     * Gets the size of products.
     *
     * @return products size
     */
    public int getNumberProducts() {
        return numberProducts;
    }

    /**
     * Gets the seat generator configuration.
     * @return
     *      seat generator configuration
     */
    public SeatConfiguration getSeatConfiguration() {
        return seatConfiguration;
    }

    /**
     * Gets the size of tariffs.
     *
     * @return tariffs size
     */
    public int getNumberTariffs() {
        return numberTariffs;
    }

    /**
     * Set baggage configuration, used for reading yml file.
     *
     * @param baggageParam
     *            baggage configuration from zml file
     */
    public void setBaggageConfiguration(
            final BaggageGeneratorConfiguration baggageParam) {
        baggageConfiguration = baggageParam;
    }

    /**
     * Sets the number of bookings.
     *
     * @param numberBookingsParam
     *            the new number bookings
     */
    public void setNumberBookings(final int numberBookingsParam) {
        numberBookings = numberBookingsParam;
    }

    /**
     * Set flight configuration, used for reading yml file.
     *
     * @param flightParam
     *            flight configuration from zml file
     */
    public void setFlightConfiguration(
            final FlightGeneratorConfiguration flightParam) {
        flightConfiguration = flightParam;
    }

    /**
     * Set max number of products, used for reading yml file.
     *
     * @param productParam
     *        product from zml file
     */
    public void setNumberProducts(final int productParam) {
        numberProducts = productParam;
    }

    /**
     * Set seat configuration, used for reading yml file.
     *
     * @param seatParam
     *            seat configuration from zml file
     */
    public void setSeatConfiguration(final SeatConfiguration seatParam) {
        seatConfiguration = seatParam;
    }

    /**
     * Set max number of tariffs, used for reading yml file.
     *
     * @param tariffParam
     *        tariff from zml file
     */
    public void setNumberTariffs(final int tariffParam) {
        numberTariffs = tariffParam;
    }
}

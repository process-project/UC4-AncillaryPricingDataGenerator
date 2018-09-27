package com.lhsystems.module.datageneratorancillary.service.generator.configuration;

/**
 * Store configuration for all generators.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public class GeneratorConfiguration {

    /** Configuration for a flight generator. */
    private FlightGeneratorConfiguration flight;

    /** Configuration for a baggage generator. */
    private BaggageGeneratorConfiguration baggage;

    /** Configuration for a seat generator. */
    private SeatConfiguration seat;

    /** Number of products that should be generated. */
    private int product;

    /** Number of tariffs that should be generated. */
    private int tariff;

    /**
     * Instantiates a new generator configuration object.
     */
    public GeneratorConfiguration() {
    }

    /**
     * Gets the baggage generator configuration .
     * @return
     *      baggage generator configuration
     */
    public BaggageGeneratorConfiguration getBaggage() {
        return baggage;
    }

    /**
     * Set baggage configuration, used for reading yml file.
     *
     * @param baggageParam
     *        baggage configuration from zml file
     */
    public void setBaggage(final BaggageGeneratorConfiguration baggageParam) {
        this.baggage = baggageParam;
    }

    /**
     * Gets the seat generator configuration.
     * @return
     *      seat generator configuration
     */
    public SeatConfiguration getSeat() {
        return seat;
    }

    /**
     * Set seat configuration, used for reading yml file.
     *
     * @param seatParam
     *        seat configuration from zml file
     */
    public void setSeat(final SeatConfiguration seatParam) {
        this.seat = seatParam;
    }

    /**
     * Gets the size of products.
     *
     * @return
     *      products size
     */
    public int getProduct() {
        return product;
    }

    /**
     * Set max number of products, used for reading yml file.
     *
     * @param productParam
     *        product from zml file
     */
    public void setProduct(final int productParam) {
        this.product = productParam;
    }

    /**
     * Gets the size of tariffs.
     * @return
     *      tariffs size
     */
    public int getTariff() {
        return tariff;
    }

    /**
     * Set max number of tariffs, used for reading yml file.
     *
     * @param tariffParam
     *        tariff from zml file
     */
    public void setTariff(final int tariffParam) {
        this.tariff = tariffParam;
    }

    /**
     * Gets the flight generator configuration.
     * @return
     *      flight generator configuration
     */
    public FlightGeneratorConfiguration getFlight() {
        return flight;
    }

    /**
     * Set flight configuration, used for reading yml file.
     *
     * @param flightParam
     *        flight configuration from zml file
     */
    public void setFlight(final FlightGeneratorConfiguration flightParam) {
        this.flight = flightParam;
    }
}

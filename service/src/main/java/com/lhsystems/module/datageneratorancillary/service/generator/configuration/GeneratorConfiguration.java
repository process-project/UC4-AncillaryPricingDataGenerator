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
     * Gets the baggage generator configuration .
     * @return
     *      baggage generator configuration
     */
    public BaggageGeneratorConfiguration getBaggage() {
        return baggage;
    }

    /**
     * Set baggage configuration, used for reading yml file
     *
     * @param baggage
     *        baggage configuration from zml file
     */
    public void setBaggage(BaggageGeneratorConfiguration baggage) {
        this.baggage = baggage;
    }

    /**
     * Gets the seat generator configuration .
     * @return
     *      seat generator configuration
     */
    public SeatConfiguration getSeat() {
        return seat;
    }

    /**
     * Set seat configuration, used for reading yml file
     *
     * @param seat
     *        seat configuration from zml file
     */
    public void setSeat(SeatConfiguration seat) {
        this.seat = seat;
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
     * Set max number of products, used for reading yml file
     *
     * @param product
     *        product from zml file
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * Gets the size of tariffs .
     * @return
     *      tariffs size
     */
    public int getTariff() {
        return tariff;
    }

    /**
     * Set max number of tariffs, used for reading yml file
     *
     * @param tariff
     *        tariff from zml file
     */
    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    /**
     * Gets the flight generator configuration .
     * @return
     *      flight generator configuration
     */
    public FlightGeneratorConfiguration getFlight() {
        return flight;
    }

    /**
     * Set flight configuration, used for reading yml file
     *
     * @param flight
     *        flight configuration from zml file
     */
    public void setFlight(FlightGeneratorConfiguration flight) {
        this.flight = flight;
    }
}

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

    public BaggageGeneratorConfiguration getBaggage() {
        return baggage;
    }

    public void setBaggage(BaggageGeneratorConfiguration baggage) {
        this.baggage = baggage;
    }

    public SeatConfiguration getSeat() {
        return seat;
    }

    public void setSeat(SeatConfiguration seat) {
        this.seat = seat;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public FlightGeneratorConfiguration getFlight() {
        return flight;
    }

    public void setFlight(FlightGeneratorConfiguration flight) {
        this.flight = flight;
    }
}

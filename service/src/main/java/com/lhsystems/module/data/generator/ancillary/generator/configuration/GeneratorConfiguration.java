package com.lhsystems.module.data.generator.ancillary.generator.configuration;

public class GeneratorConfiguration {

    private FlightGeneratorConfiguration flight;
    private BaggageGeneratorConfiguration baggage;
    private SeatConfiguration seat;
    private int product;

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

package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.SeatingModel;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;

import java.util.List;

/**
 * Randomly generates Tariffs.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class TariffGenerator extends DataGenerator {

    /** The maximal price of a flight. */
    private static final double MAXIMUM_PRICE = 600;

    /** The minimal price of a flight. */
    private static final double MINIMUM_PRICE = 40;

    /** The products to be used for tariff generation. */
    private final List<Product> products;

    /** The markets to be used for tariff generation. */
    private final List<Market> markets = Market.getAllMarkets();

    /** The seating models to be used for tariff generation. */
    private final List<SeatingModel> seatingModels;

    /**
     * Instantiates a new tariff generator.
     *
     * @param startId
     *            the smallest id used for data Generation
     * @param paramProducts
     *            the products to be used for tariff generation
     * @param paramSeatingModels
     *            the seating models to be used for tariff generation
     */
    public TariffGenerator(final Long startId,
            final List<Product> paramProducts,
            final List<SeatingModel> paramSeatingModels) {
        super(startId);
        products = paramProducts;
        seatingModels = paramSeatingModels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Tariff generate(final long id) {
        final double price = getRandom().getRandomRoundedDouble(
                MINIMUM_PRICE,
                MAXIMUM_PRICE,
                2);
        final Product product = getRandom().getOneRandomElement(products);
        final SeatingModel seatingModel = getRandom().getOneRandomElement(
                seatingModels);
        final Market market = getRandom().getOneRandomElement(markets);
        return new Tariff(id, price, seatingModel, product, market);
    }
}

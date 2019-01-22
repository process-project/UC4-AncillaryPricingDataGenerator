package com.lhsystems.module.datageneratorancillary.service.generator.core;

import com.lhsystems.module.datageneratorancillary.service.data.Market;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.data.Tariff;
import com.lhsystems.module.datageneratorancillary.service.generator.configuration.TariffConfiguration;

import java.util.List;

/**
 * Randomly generates Tariffs.
 *
 * @author REJ
 * @version $Revision: 1.10 $
 */
public final class TariffGenerator extends DataGenerator {

    /** The markets to be used for tariff generation. */
    private final List<Market> markets = Market.getAllMarkets();

    /** The maximal price of a flight. */
    private final double maximumPrice;

    /** The minimal price of a flight. */
    private final double minimumPrice;

    /** The products to be used for tariff generation. */
    private final List<Product> products;

    /**
     * Instantiates a new tariff generator.
     *
     * @param paramProducts
     *            the products to be used for tariff generation
     * @param tariffConfiguration
     *            the tariff configuration
     */
    public TariffGenerator(
            final List<Product> paramProducts,
            final TariffConfiguration tariffConfiguration) {
        products = paramProducts;
        maximumPrice = tariffConfiguration.getMaximumPrice();
        minimumPrice = tariffConfiguration.getMinimumPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Tariff generate() {
        final double price = getRandom().getRandomRoundedDouble(
                minimumPrice,
                maximumPrice,
                2);
        final Product product = getRandom().getOneRandomElement(products);
        final Market market = getRandom().getOneRandomElement(markets);
        return new Tariff(price, product, market);
    }
}

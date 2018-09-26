package com.lhsystems.module.datageneratorancillary.service.generator.starter;

import com.lhsystems.module.datageneratorancillary.service.data.BaggageClass;
import com.lhsystems.module.datageneratorancillary.service.data.Compartment;
import com.lhsystems.module.datageneratorancillary.service.data.Product;
import com.lhsystems.module.datageneratorancillary.service.generator.core.ProductGenerator;
import com.lhsystems.module.datageneratorancillary.service.repository.CompartmentRepository;
import com.lhsystems.module.datageneratorancillary.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Starts generating product entities and save them into database.
 *
 *
 * @author MB
 * @version $Revision: 1.10 $
 */
@Service
class ProductGeneratorStarter {
    /** The repository used for saving products. */
    private final ProductRepository productRepository;

    /** The repository used for saving compartments. */
    private final CompartmentRepository compartmentRepository;

    /**
     * Instantiates a new product generator starer with injected repositories
     *
     * @param productRepository
     *        repository responsible for crud operations on products entities
     * @param compartmentRepository
     *        repository responsible for crud operations on compartment entities
     */
    @Autowired
    public ProductGeneratorStarter(ProductRepository productRepository, CompartmentRepository compartmentRepository) {
        this.productRepository = productRepository;
        this.compartmentRepository = compartmentRepository;
    }

    /**
     * Generate products entities save them
     *
     * @param startId
     *        the smallest id used for data Generation
     * @param baggageClasses
     *        the tariffs to be used for flight generation
     * @param productsSize
     *        the size of products that should be generated
     * @return
     *        the list of generated products
     */
    List<Product> generateProductsEntities(long startId, List<BaggageClass> baggageClasses, int productsSize) {
        List<Compartment> compartments = generateCompartment(startId);
        return generateSeatGroups(startId, compartments, baggageClasses, productsSize);
    }

    /**
     * Generate compartment entities and save them into database
     *
     * @param startId
     *        the smallest id used for data Generation
     * @return
     *        the list of generated compartments
     */
    private List<Compartment> generateCompartment(long startId) {
        Compartment compartment = new Compartment(1, 'd', "name");
        compartmentRepository.save(compartment);
        List<Compartment> compartments = new ArrayList<>();
        compartments.add(compartment);
        return compartments;
    }

    /**
     * Generate products entities and save them into database
     *
     * @param startId
     *        the smallest id used for data Generation
     * @param compartments
     *        the compartments to be used for product generation
     * @param baggageClasses
     *        the baggage classes to be used for product generation
     * @param productsSize
     *        the size of products that should be generated
     * @return
     *        the list of generated products
     */
    private List<Product> generateSeatGroups(long startId, List<Compartment> compartments,
                                             List<BaggageClass> baggageClasses, int productsSize) {
        ProductGenerator productGenerator = new ProductGenerator(startId, compartments, baggageClasses);
        final List<Product> products = productGenerator.generateList(productsSize);
        productRepository.saveAll(products);
        return products;
    }
}

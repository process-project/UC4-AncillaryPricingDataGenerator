package com.lhsystems.module.data.generator.ancillary.generator.starter;

import com.lhsystems.module.data.generator.ancillary.data.BaggageClass;
import com.lhsystems.module.data.generator.ancillary.data.Compartment;
import com.lhsystems.module.data.generator.ancillary.data.Product;
import com.lhsystems.module.data.generator.ancillary.generator.core.ProductGenerator;
import com.lhsystems.module.data.generator.ancillary.repository.CompartmentRepository;
import com.lhsystems.module.data.generator.ancillary.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
class ProductGeneratorStarter {
    private final ProductRepository productRepository;
    private final CompartmentRepository compartmentRepository;

    @Autowired
    public ProductGeneratorStarter(ProductRepository productRepository, CompartmentRepository compartmentRepository) {
        this.productRepository = productRepository;
        this.compartmentRepository = compartmentRepository;
    }

    List<Product> generateProductsEntities(long startId, List<BaggageClass> baggageClasses, int productsSize) {
        generateCompartment(startId);
        return generateSeatGroups(startId, baggageClasses, productsSize);
    }

    private void generateCompartment(long startId) {
        Compartment compartment = new Compartment(1, 'd', "name");
        compartmentRepository.save(compartment);
    }

    private List<Product> generateSeatGroups(long startId, List<BaggageClass> baggageClasses, int productsSize) {
        ProductGenerator productGenerator = new ProductGenerator(startId, Collections.emptyList(), baggageClasses);
        final List<Product> products = productGenerator.generateList(productsSize);
        productRepository.saveAll(products);
        return products;
    }
}

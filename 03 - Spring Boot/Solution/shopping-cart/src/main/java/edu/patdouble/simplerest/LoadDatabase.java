package edu.patdouble.simplerest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * Populates the database with product information when the application starts.
 */
@Configuration
class LoadDatabase {
    @Autowired
    ProductRepository repository;

    Product saveProductIfMissing(String sku, String name, BigDecimal price) {
        if (repository.findBySku(sku).isEmpty()) {
            return repository.save(new Product(sku, name, price));
        }
        return null; // indicates we didn't create anything
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            saveProductIfMissing("SKU-1234", "Dog Whistle", BigDecimal.valueOf(10.00));
            saveProductIfMissing("SKU-1235", "Cat Collar", BigDecimal.valueOf(8.00));
        };
    }
}


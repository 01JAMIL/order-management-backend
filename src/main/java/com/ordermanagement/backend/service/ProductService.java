package com.ordermanagement.backend.service;


import com.ordermanagement.backend.common.ProductPayload;
import com.ordermanagement.backend.model.Product;
import com.ordermanagement.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing Product-related business logic and database operations.
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Retrieve all products.
     *
     * @return List of all products
     */
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    /**
     * Save a new or updated product.
     *
     * @param payload Payload to save of type ProductPayload
     * @return Saved product
     */
    public Product saveProduct(ProductPayload payload) {
        Product product = new Product();
        product.setName(payload.name());
        product.setType(payload.type());
        product.setStock(payload.stock());
        product.setSupplier(payload.supplier());

        return this.productRepository.save(product);
    }

    /**
     * Find a product by its ID.
     *
     * @param id Product ID
     * @return Optional containing the product if found, empty otherwise
     */
    public Optional<Product> findProductById(Long id) {
        return this.productRepository.findById(id);
    }


    /**
     * Update an existing product.
     *
     * @param id      ID of the product to update
     * @param product Updated product data
     * @return Optional containing the updated product if found, empty otherwise
     */
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    product.setId(id); // Ensure the ID remains unchanged
                    return productRepository.save(product);
                });
    }

    /**
     * Delete a product by its ID.
     *
     * @param id Product ID to delete
     */
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}

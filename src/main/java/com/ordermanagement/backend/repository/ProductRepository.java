package com.ordermanagement.backend.repository;

import com.ordermanagement.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Product entity, providing CRUD operations.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}

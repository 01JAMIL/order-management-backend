package com.ordermanagement.backend.repository;

import com.ordermanagement.backend.model.ManufacturingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Repository interface for ManufacturingOrder entity, providing CRUD operations.
 * */

public interface ManufacturingOrderRepository extends JpaRepository<ManufacturingOrder, Long> {
}

package com.ordermanagement.backend.repository;

import com.ordermanagement.backend.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Machine entity, providing CRUD operations.
 */


public interface MachineRepository extends JpaRepository<Machine, Long> {
}

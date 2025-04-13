package com.ordermanagement.backend.repository;


import com.ordermanagement.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Employee entity, providing CRUD operations.
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
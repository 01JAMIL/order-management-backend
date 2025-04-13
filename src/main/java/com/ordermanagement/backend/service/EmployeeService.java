package com.ordermanagement.backend.service;

import com.ordermanagement.backend.common.EmployeePayload;
import com.ordermanagement.backend.model.Employee;
import com.ordermanagement.backend.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Employee-related business logic and database operations.
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Retrieve all employees.
     *
     * @return List of all employees
     */
    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    /**
     * Save a new employee.
     *
     * @param payload Payload to save of type EmployeePayload
     * @return Saved employee
     */
    @Transactional
    public Employee saveEmployee(EmployeePayload payload) {
        Employee employee = new Employee();
        employee.setName(payload.name());
        employee.setPosition(payload.position());

        return this.employeeRepository.save(employee);
    }

    /**
     * Find an employee by their ID.
     *
     * @param id Employee ID
     * @return Optional containing the employee if found, empty otherwise
     */
    public Optional<Employee> findEmployeeById(Long id) {
        return this.employeeRepository.findById(id);
    }

    /**
     * Update an existing employee.
     *
     * @param id      ID of the employee to update
     * @param payload Updated employee data
     * @return Optional containing the updated employee if found, empty otherwise
     */
    @Transactional
    public Optional<Employee> updateEmployee(Long id, EmployeePayload payload) {
        Employee employeeToUpdate = new Employee();
        employeeToUpdate.setId(id);
        employeeToUpdate.setName(payload.name());
        employeeToUpdate.setPosition(payload.position());

        return this.employeeRepository.findById(id)
                .map(existingEmployee -> {
                    employeeToUpdate.setId(id); // Ensure the ID remains unchanged
                    return this.employeeRepository.save(employeeToUpdate);
                });
    }

    /**
     * Delete an employee by their ID.
     *
     * @param id Employee ID to delete
     */
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }
}

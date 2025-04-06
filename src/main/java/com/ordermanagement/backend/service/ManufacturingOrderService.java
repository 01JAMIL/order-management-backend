package com.ordermanagement.backend.service;


import com.ordermanagement.backend.common.CreateManufacturingOrderPayload;
import com.ordermanagement.backend.common.ManufacturingOrderStatus;
import com.ordermanagement.backend.common.UpdateManufacturingOrderPayload;
import com.ordermanagement.backend.model.ManufacturingOrder;
import com.ordermanagement.backend.model.Product;
import com.ordermanagement.backend.repository.ManufacturingOrderRepository;
import com.ordermanagement.backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing ManufacturingOrder-related business logic and database operations.
 */

@Service
@AllArgsConstructor
public class ManufacturingOrderService {
    private final ManufacturingOrderRepository manufacturingOrderRepository;
    private final ProductRepository productRepository;

    /**
     * Create a new manufacturing order.
     *
     * @param payload Payload to save of type ManufacturingOrderPayload
     * @Return Saved manufacturing order
     */

    @Transactional
    public ManufacturingOrder createOrder(CreateManufacturingOrderPayload payload) {
        Product product = productRepository.findById(payload.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + payload.productId()));

        ManufacturingOrder order = new ManufacturingOrder();
        order.setProject(payload.project());
        order.setStatus(ManufacturingOrderStatus.PENDING);
        order.setQuantity(payload.quantity());
        order.setDate(payload.date());
        order.setProduct(product);

        return manufacturingOrderRepository.save(order);
    }

    /**
     * Update an existing manufacturing order.
     *
     * @param id      ID of the manufacturing order to update
     * @param payload Payload containing updated data
     * @return Updated manufacturing order
     * @throws IllegalArgumentException if the order or product is not found
     */
    @Transactional
    public ManufacturingOrder updateOrder(Long id, UpdateManufacturingOrderPayload payload) {
        ManufacturingOrder order = manufacturingOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturing order not found with ID: " + id));

        Product product = productRepository.findById(payload.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + payload.productId()));

        order.setProject(payload.project());
        order.setStatus(payload.status());
        order.setQuantity(payload.quantity());
        order.setDate(payload.date());
        order.setProduct(product);

        return manufacturingOrderRepository.save(order);
    }

    /**
     * Delete a manufacturing order by its ID.
     *
     * @param id ID of the manufacturing order to delete
     * @throws IllegalArgumentException if the order is not found
     */
    @Transactional
    public void deleteOrder(Long id) {
        ManufacturingOrder order = manufacturingOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturing order not found with ID: " + id));
        manufacturingOrderRepository.delete(order);
    }

    /**
     * Retrieve all manufacturing orders.
     *
     * @Return List of all manufacturing orders
     */

    public List<ManufacturingOrder> getAllOrders() {
        return manufacturingOrderRepository.findAll();
    }


    /**
     * Retrieve a manufacturing order by its ID.
     *
     * @param id Manufacturing order ID
     * @return Optional containing the manufacturing order if found, empty otherwise
     */
    public Optional<ManufacturingOrder> getOrderById(Long id) {
        return manufacturingOrderRepository.findById(id);
    }


    /**
     * Update Manufacturing status by ID
     *
     * @param id Manufacturing order ID
     * @return Optional containing the manufacturing order if found, empty otherwise
     * @throws IllegalArgumentException if the order is not found
     */
    @Transactional
    public ManufacturingOrder updateStatus(Long id, ManufacturingOrderStatus status) {
        ManufacturingOrder order = manufacturingOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manufacturing order not found with ID: " + id));
        order.setStatus(status);
        return manufacturingOrderRepository.save(order);
    }
}

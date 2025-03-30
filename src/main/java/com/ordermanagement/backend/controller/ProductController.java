package com.ordermanagement.backend.controller;


import com.ordermanagement.backend.common.ProductPayload;
import com.ordermanagement.backend.model.Product;
import com.ordermanagement.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieve all products.
     *
     * @return List of all products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.findAllProducts());
    }

    /**
     * Create a new product.
     *
     * @param payload Payload to create
     * @return Created product
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody ProductPayload payload
    ) {
        Product savedProduct = this.productService.saveProduct(payload);
        return ResponseEntity.ok(savedProduct);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        if (message.contains("ProductType")) {
            return Map.of("type", "Invalid product type. Must be one of: PART, ASSEMBLY, MATERIAL");
        }
        return Map.of("error", "Invalid request body: " + message);
    }
}

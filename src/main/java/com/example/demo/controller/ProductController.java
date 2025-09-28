package com.example.demo.controller;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        ProductResponseDTO productResponseDTO = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));

        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductRequestDTO request) {
        return new ResponseEntity<>(productService.updateProduct(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.NO_CONTENT);
    }
}

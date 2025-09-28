package com.example.demo.service;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductResponseDTO)
                .toList();
    }

    public Optional<ProductResponseDTO> getProductById(UUID id) {
        return productRepository.findById(id)
                .map(ProductMapper::toProductResponseDTO);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = ProductMapper.toProduct(requestDTO);
        Product savedEntity = productRepository.save(product);
        return ProductMapper.toProductResponseDTO(savedEntity);
    }

    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));

        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());

        return ProductMapper.toProductResponseDTO(productRepository.save(product));
    }

    public ProductResponseDTO deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));

        return ProductMapper.toProductResponseDTO(product);
    }
}

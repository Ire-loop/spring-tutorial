package com.example.demo.mapper;

import com.example.demo.dto.request.ProductRequestDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.exception.InvalidProductCategoryException;
import com.example.demo.model.Product;
import com.example.demo.model.ProductCategory;

public class ProductMapper {

    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .productCategory(
                        product.getCategory() != null ? product.getCategory().name() : null
                )
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public static Product toProduct(ProductRequestDTO requestDTO) {
        try {
            return Product.builder()
                    .name(requestDTO.getName())
                    .description(requestDTO.getDescription())
                    .brand(requestDTO.getBrand())
                    .category(ProductCategory.valueOf(requestDTO.getProductCategory().toUpperCase()))
                    .price(requestDTO.getPrice())
                    .quantity(requestDTO.getQuantity())
                    .build();
        } catch (IllegalArgumentException exception) {
            throw new InvalidProductCategoryException(requestDTO.getProductCategory());
        }
    }
}

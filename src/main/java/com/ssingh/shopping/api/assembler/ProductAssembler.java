package com.ssingh.shopping.api.assembler;

import com.ssingh.shopping.api.dto.NewProductDto;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.persistence.entity.Product;

public class ProductAssembler {

    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product toProduct(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
    }

    public static Product toProduct(NewProductDto newProductDto) {
        return Product.builder()
                .name(newProductDto.getName())
                .description(newProductDto.getDescription())
                .price(newProductDto.getPrice())
                .build();
    }

}

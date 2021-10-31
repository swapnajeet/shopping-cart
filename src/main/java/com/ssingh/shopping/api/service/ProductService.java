package com.ssingh.shopping.api.service;

import org.springframework.stereotype.Service;
import com.ssingh.shopping.api.assembler.ProductAssembler;
import com.ssingh.shopping.api.dto.NewProductDto;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.error.ProductNotFoundException;
import com.ssingh.shopping.api.persistence.entity.Product;
import com.ssingh.shopping.api.persistence.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = repository.findAll();
        return productList.stream().map(ProductAssembler::toProductDto).collect(Collectors.toList());
    }

    public ProductDto addProduct(NewProductDto newProductDto) {
        Product createdProduct = repository.save(ProductAssembler.toProduct(newProductDto));
        return ProductAssembler.toProductDto(createdProduct);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        validateProductExists(productDto.getProductId());
        Product updatedProduct = repository.save(ProductAssembler.toProduct(productDto));
        return ProductAssembler.toProductDto(updatedProduct);
    }

    public void removeProduct(Integer productId) {
        validateProductExists(productId);
        repository.deleteById(productId);
    }

    public Product getProductById(Integer productId) {
        Optional<Product> productOptional = repository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(String.format("No product found with id %d", productId));
        }
        return productOptional.get();
    }

    private void validateProductExists(Integer productId) {
        if (repository.findById(productId).isEmpty()) {
            throw new ProductNotFoundException(String.format("No product found with id %d", productId));
        }
    }
}

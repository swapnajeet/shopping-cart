package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.NewProductDto;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.service.ProductService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid NewProductDto newProductDto) {
        ProductDto createdProduct = productService.addProduct(newProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable Integer productId) {
        productService.removeProduct(productId);
        return ResponseEntity.ok(String.format("Product %d removed successfully.", productId));
    }
}

package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.service.ProductSearchService;
import java.util.List;

@RestController
@RequestMapping("api/v1/products/search")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> searchProductByNameMatchingText(@RequestParam String textToSearch, @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        List<ProductDto> matchedProducts = productSearchService.getProductByNameMatchingText(textToSearch, pageIndex, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(matchedProducts);
    }
}

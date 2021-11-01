package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.PagingInfoDto;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.service.ProductSearchService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/products/search")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/{textToSearch}")
    public ResponseEntity<List<ProductDto>> updateProduct(@PathVariable String textToSearch, @Valid PagingInfoDto pagingInfoDto) {
        List<ProductDto> matchedProducts = productSearchService.searchProducts(textToSearch, pagingInfoDto.getStartIndex(), pagingInfoDto.getPageSize());
        return ResponseEntity.status(HttpStatus.OK).body(matchedProducts);
    }
}

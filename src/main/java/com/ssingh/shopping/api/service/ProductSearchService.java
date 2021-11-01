package com.ssingh.shopping.api.service;

import org.springframework.stereotype.Service;
import com.ssingh.shopping.api.assembler.ProductAssembler;
import com.ssingh.shopping.api.dto.NewProductDto;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.error.ProductNotFoundException;
import com.ssingh.shopping.api.persistence.entity.Product;
import com.ssingh.shopping.api.persistence.repository.ProductRepository;
import com.ssingh.shopping.api.persistence.repository.ProductSearchRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    private ProductSearchRepository searchRepository;

    public ProductSearchService(ProductSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<ProductDto> searchProducts(String searchText, int start, int count) {
        List<Product> products = searchRepository.getProductNameByMatchingText(searchText, start, count);
        return products.stream().map(ProductAssembler::toProductDto).collect(Collectors.toList());
    }

}

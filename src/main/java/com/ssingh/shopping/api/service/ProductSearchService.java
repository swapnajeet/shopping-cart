package com.ssingh.shopping.api.service;

import lombok.RequiredArgsConstructor;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import com.ssingh.shopping.api.assembler.ProductAssembler;
import com.ssingh.shopping.api.dto.ProductDto;
import com.ssingh.shopping.api.persistence.entity.Product;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductSearchService {

    private final EntityManager entityManager;

    public List<ProductDto> getProductByNameMatchingText(String text, int pageIndex, int pageSize) {
        FullTextEntityManager textEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = textEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();

        Query query = queryBuilder.keyword().onField("name").matching(text).createQuery();

        FullTextQuery fullTextQuery = textEntityManager.createFullTextQuery(query, Product.class);
        fullTextQuery.setFirstResult(pageIndex);
        fullTextQuery.setMaxResults(pageIndex * pageSize);
        List<Product> resultList = (List<Product>) fullTextQuery.getResultList();
        return resultList.stream().map(ProductAssembler::toProductDto).collect(Collectors.toList());
    }
}

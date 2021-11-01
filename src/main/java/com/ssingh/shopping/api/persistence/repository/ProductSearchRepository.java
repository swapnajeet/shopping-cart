package com.ssingh.shopping.api.persistence.repository;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import com.ssingh.shopping.api.persistence.entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getProductNameByMatchingText(String text, int start, int count) {
        FullTextEntityManager textEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = textEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
        Query query = queryBuilder.keyword().onField("name").matching(text).createQuery();

        FullTextQuery fullTextQuery = textEntityManager.createFullTextQuery(query, Product.class);
        fullTextQuery.setFirstResult(start);
        fullTextQuery.setMaxResults(count);
        return (List<Product>) fullTextQuery.getResultList();
    }
}

package com.ssingh.shopping.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssingh.shopping.api.persistence.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

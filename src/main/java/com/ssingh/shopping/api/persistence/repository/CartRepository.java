package com.ssingh.shopping.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssingh.shopping.api.persistence.entity.CartItem;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findAllByUserId(String userName);
}

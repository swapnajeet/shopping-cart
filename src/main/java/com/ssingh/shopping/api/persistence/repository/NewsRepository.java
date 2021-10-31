package com.ssingh.shopping.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssingh.shopping.api.persistence.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}

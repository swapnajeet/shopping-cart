package com.ssingh.shopping.api.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ssingh.shopping.api.persistence.entity.Product;
import java.util.List;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductSearchRepositoryTest {

    @Autowired
    ProductSearchRepository searchRepository;

    @Test
    public void testFreeTextSearchForProducts() {
        List<Product> result = searchRepository.getProductNameByMatchingText("test", 1, 1);
    }

}
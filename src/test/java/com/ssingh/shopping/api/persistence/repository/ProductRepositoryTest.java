package com.ssingh.shopping.api.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ssingh.shopping.api.persistence.entity.Product;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    public void testCreateReadDelete() {
        Product testProduct = Product.builder().productId(123).name("Test product").description("Created from test.").price(200.0).build();

        repository.save(testProduct);

        List<Product> products = repository.findAll();
        assertThat(products).extracting(Product::getProductId).containsOnly(123);

        repository.deleteById(123);
        assertThat(repository.findAll()).isEmpty();
    }
}
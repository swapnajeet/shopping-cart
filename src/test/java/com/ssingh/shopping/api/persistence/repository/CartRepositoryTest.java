package com.ssingh.shopping.api.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ssingh.shopping.api.persistence.entity.CartItem;
import com.ssingh.shopping.api.persistence.entity.Product;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Test
    public void testCreateReadDelete() {
        Product testProduct = Product.builder().productId(123).name("Test product").description("Created from test.").price(200.0).build();
        productRepository.save(testProduct);

        CartItem cartItem = CartItem.builder().product(testProduct).quantity(2).createdDate(new Date()).userId("test_user").build();

        cartRepository.save(cartItem);

        List<CartItem> cartItems = cartRepository.findAll();
        assertThat(cartItems).hasSize(1);

        CartItem persistedCartItem = cartItems.get(0);
        assertThat(cartItems).isNotNull();

        cartRepository.deleteById(persistedCartItem.getEntryId());
        assertThat(cartRepository.findAll()).isEmpty();
    }
}
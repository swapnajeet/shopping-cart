package com.ssingh.shopping.api.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ssingh.shopping.api.constants.OrderStatus;
import com.ssingh.shopping.api.persistence.entity.Order;
import com.ssingh.shopping.api.persistence.entity.OrderItem;
import com.ssingh.shopping.api.persistence.entity.Product;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testCreateReadDelete() {

        Product testProduct = Product.builder().productId(123).name("Test product").description("Created from test.").price(200.0).build();
        productRepository.save(testProduct);

        OrderItem testOrderItem = OrderItem.builder().product(testProduct).createdDate(new Date()).quantity(4).build();

        Order testOrder = Order.builder()
                .orderDate(new Date())
                .orderStatus(OrderStatus.NEW.getValue())
                .totalPrice(5000.0)
                .userId("test_user")
                .orderItems(Collections.singletonList(testOrderItem))
                .build();

        orderRepository.save(testOrder);

        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);

        Order persistedOrder = orders.get(0);
        assertThat(persistedOrder).isNotNull();

        orderRepository.deleteById(persistedOrder.getOrderId());
        assertThat(orderRepository.findAll()).isEmpty();
    }

}
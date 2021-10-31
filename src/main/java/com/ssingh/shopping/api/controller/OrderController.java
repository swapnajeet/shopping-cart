package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.dto.OrderDto;
import com.ssingh.shopping.api.service.CartService;
import com.ssingh.shopping.api.service.OrderService;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private OrderService orderService;

    private CartService cartService;

    private final Authentication authentication;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> allOrdersForUser = orderService.getAllOrdersForUser(authentication.getName());
        return ResponseEntity.ok(allOrdersForUser);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createOrder() {
        CheckoutSummaryDto checkoutSummaryForUser = cartService.getCheckoutSummaryForUser(authentication.getName());
        OrderDto order = orderService.createOrder(checkoutSummaryForUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Order has been created successfully. Order id: %d", order.getOrderId()));
    }
}

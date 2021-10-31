package com.ssingh.shopping.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssingh.shopping.api.assembler.OrderAssembler;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.dto.OrderDto;
import com.ssingh.shopping.api.persistence.entity.Order;
import com.ssingh.shopping.api.persistence.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(CheckoutSummaryDto checkoutSummary) {
        return OrderAssembler.toOrderDto(checkoutSummary);
    }

    public List<OrderDto> getAllOrdersForUser(String userId) {
        List<Order> allOrdersForUser = orderRepository.findAllByUserId(userId);
        return allOrdersForUser.stream().map(OrderAssembler::toOrderDto).collect(Collectors.toList());
    }
}

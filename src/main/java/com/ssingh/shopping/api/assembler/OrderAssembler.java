package com.ssingh.shopping.api.assembler;

import com.ssingh.shopping.api.constants.OrderStatus;
import com.ssingh.shopping.api.dto.CheckoutItemDto;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.dto.OrderDto;
import com.ssingh.shopping.api.dto.OrderItemDto;
import com.ssingh.shopping.api.persistence.entity.Order;
import com.ssingh.shopping.api.persistence.entity.OrderItem;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderAssembler {
    public static OrderDto toOrderDto(Order order) {
        List<OrderItemDto> orderItemDtoList = order.getOrderItems().stream()
                .map(OrderAssembler::toOrderItemDto)
                .collect(Collectors.toList());

        return OrderDto.builder()
                .orderId(order.getOrderId())
                .createdDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .orderItems(orderItemDtoList)
                .build();

    }

    public static OrderItemDto toOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .orderItemId(orderItem.getOrderItemId())
                .orderId(orderItem.getOrder().getOrderId())
                .productId(orderItem.getProduct().getProductId())
                .quantity(orderItem.getQuantity())
                .createdDate(orderItem.getCreatedDate())
                .build();
    }

    public static OrderDto toOrderDto(CheckoutSummaryDto checkoutSummary) {
        List<OrderItemDto> orderItemDtoList = checkoutSummary.getCheckoutItems().stream()
                .map(OrderAssembler::toOrderItemDto)
                .collect(Collectors.toList());
        return OrderDto.builder()
                .createdDate(new Date())
                .orderStatus(OrderStatus.NEW.getValue())
                .totalPrice(checkoutSummary.getTotalPrice())
                .orderItems(orderItemDtoList)
                .build();
    }

    public static OrderItemDto toOrderItemDto(CheckoutItemDto checkoutItem) {
        return OrderItemDto.builder()
                .productId(checkoutItem.getProductId())
                .quantity(checkoutItem.getQuantity())
                .createdDate(checkoutItem.getDate())
                .build();
    }
}

package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class OrderDto {
    private Integer orderId;
    private Date createdDate;
    private String orderStatus;
    private Double totalPrice;
    private List<OrderItemDto> orderItems;
}

package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import com.ssingh.shopping.api.persistence.entity.Order;
import java.util.Date;

@Builder
@Data
public class OrderItemDto {
    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double unitPrice;
    private Date createdDate;
}

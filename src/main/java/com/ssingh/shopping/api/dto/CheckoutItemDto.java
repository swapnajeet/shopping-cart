package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CheckoutItemDto {
    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
    private Double totalPrice;
    private Date date;
}

package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class CheckoutSummaryDto {
    private List<CheckoutItemDto> checkoutItems;
    private String userId;
    private Date checkOutDate;
    private Double totalPrice;
}

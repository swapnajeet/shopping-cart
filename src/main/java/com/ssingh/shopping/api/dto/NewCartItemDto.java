package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
public class NewCartItemDto {
    @NotNull
    private Integer productId;
    @NotNull
    private Double unitPrice;
    @NotNull
    private Integer quantity;
    @NotNull
    private Date date;
}

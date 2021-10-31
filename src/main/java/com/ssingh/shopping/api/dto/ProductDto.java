package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class ProductDto {
    Integer productId;
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    Double price;
}

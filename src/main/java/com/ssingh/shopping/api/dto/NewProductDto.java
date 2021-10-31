package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class NewProductDto {
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    Double price;
}

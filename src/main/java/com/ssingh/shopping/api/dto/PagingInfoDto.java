package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PagingInfoDto {
    @NotNull
    private Integer startIndex;
    @NotNull
    private Integer  pageSize;
}

package com.ssingh.shopping.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
public class NewsDto {
    private Integer newsId;
    @NotNull
    private String content;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
}

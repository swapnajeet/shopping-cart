package com.ssingh.shopping.api.assembler;

import com.ssingh.shopping.api.dto.NewNewsDto;
import com.ssingh.shopping.api.dto.NewsDto;
import com.ssingh.shopping.api.persistence.entity.News;

public class NewsAssembler {

    public static NewsDto toNewsDto(News news) {
        return NewsDto.builder()
                .newsId(news.getNewsId())
                .content(news.getContent())
                .startDate(news.getStartDate())
                .endDate(news.getEndDate())
                .build();
    }

    public static News toNews(NewsDto newsDto) {
        return News.builder()
                .newsId(newsDto.getNewsId())
                .content(newsDto.getContent())
                .startDate(newsDto.getStartDate())
                .endDate(newsDto.getEndDate())
                .build();
    }

    public static News toNews(NewNewsDto newNewsDto) {
        return News.builder()
                .content(newNewsDto.getContent())
                .startDate(newNewsDto.getStartDate())
                .endDate(newNewsDto.getEndDate())
                .build();
    }
}

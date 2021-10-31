package com.ssingh.shopping.api.service;

import org.springframework.stereotype.Service;
import com.ssingh.shopping.api.assembler.NewsAssembler;
import com.ssingh.shopping.api.dto.NewNewsDto;
import com.ssingh.shopping.api.dto.NewsDto;
import com.ssingh.shopping.api.error.NewsNotFoundException;
import com.ssingh.shopping.api.persistence.entity.News;
import com.ssingh.shopping.api.persistence.repository.NewsRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public List<NewsDto> getAllNews() {
        List<News> newsList = repository.findAll();
        return newsList.stream().map(NewsAssembler::toNewsDto).collect(Collectors.toList());
    }

    public NewsDto addNews(NewNewsDto newNewsDto) {
        News createdNews = repository.save(NewsAssembler.toNews(newNewsDto));
        return NewsAssembler.toNewsDto(createdNews);
    }

    public NewsDto updateNews(NewsDto newsDto) {
        validateNewsExists(newsDto.getNewsId());
        News createdNews = repository.save(NewsAssembler.toNews(newsDto));
        return NewsAssembler.toNewsDto(createdNews);
    }

    public void deleteNews(Integer newsId) {
        validateNewsExists(newsId);
        repository.deleteById(newsId);
    }

    private void validateNewsExists(Integer newsId) {
        if (repository.findById(newsId).isEmpty()) {
            throw new NewsNotFoundException(String.format("No news found with id %d", newsId));
        }
    }
}

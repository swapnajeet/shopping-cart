package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.NewNewsDto;
import com.ssingh.shopping.api.dto.NewsDto;
import com.ssingh.shopping.api.service.NewsService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<NewsDto>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PostMapping("/add")
    public ResponseEntity<NewsDto> addNews(@RequestBody @Valid NewNewsDto newNewsDto) {
        NewsDto createdNews = newsService.addNews(newNewsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PostMapping("/update/{newsId}")
    public ResponseEntity<NewsDto> updateNews(@PathVariable Integer newsId, @RequestBody NewsDto newsDto) {
        NewsDto updatedNews = newsService.updateNews(newsDto);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<String> deleteNews(@PathVariable Integer newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.ok(String.format("News %d removed successfully.", newsId));
    }
}

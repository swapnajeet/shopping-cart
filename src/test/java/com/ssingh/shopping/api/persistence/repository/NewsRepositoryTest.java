package com.ssingh.shopping.api.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.ssingh.shopping.api.persistence.entity.News;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@AutoConfigureTestDatabase
class NewsRepositoryTest {

    @Autowired
    NewsRepository repository;

    @Test
    public void testCreateReadDelete() {
        Date today = new Date();
        String content = "This is a news created from test.";
        News testNews = News.builder().newsId(123).content(content).startDate(today).endDate(today).build();

        repository.save(testNews);

        List<News> newsList = repository.findAll();
        assertThat(newsList).extracting(News::getContent).containsOnly(content);

        repository.deleteById(123);
        assertThat(repository.findAll()).isEmpty();
    }
}
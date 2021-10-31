package com.ssingh.shopping.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssingh.shopping.api.dto.NewsDto;
import java.util.Date;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@AutoConfigureTestDatabase
class NewsServiceITest {

    @LocalServerPort
    private int serverPort;

    private String baseUrl;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + serverPort + "api/v1/news";
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser()
    void addNewsWithNonAdminUserShouldNotBePermitted() throws Exception {
        Date today = new Date();
        NewsDto newsDto = NewsDto.builder().content("News from integration test.").startDate(today).endDate(today).build();
        mockMvc.perform(post(baseUrl + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addNewsWithAdminUserShouldBePermitted() throws Exception {
        Date today = new Date();
        NewsDto newsDto = NewsDto.builder().content("News from integration test.").startDate(today).endDate(today).build();
        mockMvc.perform(post(baseUrl + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("News from integration test.")));
    }
}
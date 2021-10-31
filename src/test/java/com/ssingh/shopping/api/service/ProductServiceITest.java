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
import com.ssingh.shopping.api.dto.ProductDto;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@AutoConfigureTestDatabase
class ProductServiceITest {

    @LocalServerPort
    int serverPort;

    String baseUrl;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + serverPort + "api/v1/products";
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser()
    void addProductWithNonAdminUserShouldNotBePermitted() throws Exception {
        ProductDto productDto = ProductDto.builder().name("Test product").description("Created from test.").price(200.0).build();
        mockMvc.perform(post(baseUrl + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addProductWithAdminUserShouldBePermitted() throws Exception {
        ProductDto productDto = ProductDto.builder().name("Test product").description("Created from test.").price(200.0).build();
        mockMvc.perform(post(baseUrl + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Test product")));
    }

    void addProduct() throws Exception {

    }
}
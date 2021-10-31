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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssingh.shopping.api.dto.CartItemDto;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@AutoConfigureTestDatabase
class CartServiceITest {

    @LocalServerPort
    int serverPort;

    String baseUrl;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + serverPort + "api/v1/cart";
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser()
    void addToCartWithNonCustomerUserShouldNotBePermitted() throws Exception {
        CartItemDto cartItemDto = CartItemDto.builder().productId(2234).quantity(3).unitPrice(400.0).date(new Date()).build();
        mockMvc.perform(post(baseUrl + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartItemDto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test_user", roles = "CUSTOMER")
    @Sql("/product_data.sql")
    void addToCartWithCustomerUserShouldBePermitted() throws Exception {
        CartItemDto cartItemDto = CartItemDto.builder().productId(100).quantity(3).unitPrice(200.0).date(new Date()).build();
        mockMvc.perform(post(baseUrl + "/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartItemDto)))
                .andExpect(status().isCreated());
    }

}
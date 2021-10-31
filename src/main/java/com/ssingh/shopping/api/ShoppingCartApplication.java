package com.ssingh.shopping.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info =
@Info(title = "Shopping cart API",
        description = "Service to enable shopping cart operations.",
        contact = @Contact(name = "Swapnajeet Singh", email = "swapnajeetsingh05@gmail.com")
)
)
@SpringBootApplication
public class ShoppingCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }
}

package com.ssingh.shopping.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssingh.shopping.api.dto.CartItemDto;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.dto.NewCartItemDto;
import com.ssingh.shopping.api.error.NoAuthenticatedUserFound;
import com.ssingh.shopping.api.persistence.entity.Product;
import com.ssingh.shopping.api.service.CartService;
import com.ssingh.shopping.api.service.ProductService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        List<CartItemDto> allCartItems = cartService.getAllCartItems(getCustomerId());
        return ResponseEntity.ok(allCartItems);
    }

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody @Valid NewCartItemDto newCartItemDto) {
        Product product = productService.getProductById(newCartItemDto.getProductId());
        CartItemDto addedCartItem = cartService.addCartItem(newCartItemDto, product, getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCartItem);
    }

    @GetMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Integer cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok(String.format("Item %d removed successfully from the cart.", cartItemId));
    }

    @GetMapping("/checkout")
    public ResponseEntity<CheckoutSummaryDto> checkout() {
        CheckoutSummaryDto checkoutSummary = cartService.checkout(getCustomerId());
        return ResponseEntity.ok(checkoutSummary);
    }

    private String getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NoAuthenticatedUserFound("No authenticated user found from the security context.");
        }
        return authentication.getName();
    }
}

package com.ssingh.shopping.api.assembler;

import com.ssingh.shopping.api.dto.CartItemDto;
import com.ssingh.shopping.api.dto.NewCartItemDto;
import com.ssingh.shopping.api.persistence.entity.CartItem;
import com.ssingh.shopping.api.persistence.entity.Product;

public class CartItemAssembler {

    public static CartItemDto toCartItemDto(CartItem cartItem) {
        return CartItemDto.builder()
                .entryId(cartItem.getEntryId())
                .productId(cartItem.getProduct().getProductId())
                .quantity(cartItem.getQuantity())
                .unitPrice(cartItem.getProduct().getPrice())
                .date(cartItem.getCreatedDate())
                .build();
    }

    public static CartItem toCartItem(CartItemDto cartItemDto, Product product, String userId) {
        return CartItem.builder()
                .product(product)
                .quantity(cartItemDto.getQuantity())
                .createdDate(cartItemDto.getDate())
                .userId(userId)
                .build();
    }

    public static CartItem toCartItem(NewCartItemDto newCartItemDto, Product product, String userId) {
        return CartItem.builder()
                .product(product)
                .quantity(newCartItemDto.getQuantity())
                .createdDate(newCartItemDto.getDate())
                .userId(userId)
                .build();
    }
}

package com.ssingh.shopping.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssingh.shopping.api.assembler.CartItemAssembler;
import com.ssingh.shopping.api.assembler.CheckoutSummaryBuilder;
import com.ssingh.shopping.api.dto.CartItemDto;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.dto.NewCartItemDto;
import com.ssingh.shopping.api.error.ProductNotFoundException;
import com.ssingh.shopping.api.persistence.entity.CartItem;
import com.ssingh.shopping.api.persistence.entity.Product;
import com.ssingh.shopping.api.persistence.repository.CartRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CheckoutSummaryDto checkout(String userId) {
        return getCheckoutSummaryForUser(userId);
    }

    public List<CartItemDto> getAllCartItems(String userId) {
        List<CartItem> cartItemsForUser = cartRepository.findAllByUserId(userId);
        return cartItemsForUser.stream().map(CartItemAssembler::toCartItemDto).collect(Collectors.toList());
    }

    public CartItemDto addCartItem(NewCartItemDto newCartItemDto, Product product, String userId) {
        CartItem cartItem = CartItemAssembler.toCartItem(newCartItemDto, product, userId);
        cartItem.setUserId(userId);
        CartItem addedCartItem = cartRepository.save(cartItem);
        return CartItemAssembler.toCartItemDto(addedCartItem);
    }

    public void removeCartItem(Integer cartItemId) {
        validateCartItemExists(cartItemId);
        cartRepository.deleteById(cartItemId);
    }

    public CheckoutSummaryDto getCheckoutSummaryForUser(String userId) {
        List<CartItem> cartItemsForUser = cartRepository.findAllByUserId(userId);
        return CheckoutSummaryBuilder.builder().userId(userId).cartItems(cartItemsForUser).build();
    }

    private void validateCartItemExists(Integer cartItemId) {
        if (cartRepository.findById(cartItemId).isEmpty()) {
            throw new ProductNotFoundException(String.format("No cart item found with id %d", cartItemId));
        }
    }
}

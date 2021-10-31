package com.ssingh.shopping.api.assembler;

import lombok.Builder;
import lombok.Setter;

import com.ssingh.shopping.api.dto.CheckoutItemDto;
import com.ssingh.shopping.api.dto.CheckoutSummaryDto;
import com.ssingh.shopping.api.persistence.entity.CartItem;
import com.ssingh.shopping.api.persistence.entity.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Builder(builderClassName = "Builder")
public class CheckoutSummaryBuilder {

    List<CartItem> cartItems;
    String userId;

    public static class Builder {
        public CheckoutSummaryDto build() {
            Date today = new Date();
            List<CheckoutItemDto> checkoutItemDtos = new ArrayList<>();
            double totalPriceForCart = 0;
            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                Double productPrice = product.getPrice();
                Integer quantity = cartItem.getQuantity();
                double totalPriceForProduct = productPrice * quantity;
                totalPriceForCart += totalPriceForProduct;

                CheckoutItemDto checkoutItemDto = CheckoutItemDto.builder()
                        .productId(product.getProductId())
                        .quantity(quantity)
                        .unitPrice(productPrice)
                        .totalPrice(productPrice * quantity)
                        .date(cartItem.getCreatedDate())
                        .build();
                checkoutItemDtos.add(checkoutItemDto);

            }
            return CheckoutSummaryDto.builder()
                    .checkoutItems(checkoutItemDtos)
                    .totalPrice(totalPriceForCart)
                    .userId(userId)
                    .checkOutDate(new Date())
                    .build();
        }
    }
}

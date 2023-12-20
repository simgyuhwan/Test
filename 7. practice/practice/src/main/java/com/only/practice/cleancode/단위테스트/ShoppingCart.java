package com.only.practice.cleancode.단위테스트;

import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니
 */
public class ShoppingCart {
    private final List<Product> products;

    ShoppingCart() {
        products = new ArrayList<>();
    }

    private ShoppingCart(List<Product> products) {
        this.products = products;
    }

    /**
     * 장바구니에 상품 추가하기
     *
     * @param product 상품
     * @return 상품에 추가된 장바구니
     */
    ShoppingCart add(final Product product) {
        final List<Product> adding = new ArrayList<>(products);
        adding.add(product);
        return new ShoppingCart(adding);
    }

    /**
     * @return 상품 합계 금액
     */
    int totalPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}

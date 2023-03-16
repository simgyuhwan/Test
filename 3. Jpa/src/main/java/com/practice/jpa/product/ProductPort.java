package com.practice.jpa.product;

public interface ProductPort {
    void save(final Product product);

    Product getProduct(long productId);
}

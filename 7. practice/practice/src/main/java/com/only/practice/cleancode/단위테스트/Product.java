package com.only.practice.cleancode.단위테스트;

public class Product {
    final int id;
    final String name;
    final int price;

    public Product(final int id, final String name, final int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}


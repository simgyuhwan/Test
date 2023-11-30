package com.only.practice.cleancode.결합도;

import java.util.List;

/**
 * Created by Gyuhwan
 */
public class DiscountManager {

  List<Product> discountProducts;
  int totalPrice;

  boolean add(Product product, ProductDiscount productDiscount) {
    if (product.id < 0) {
      throw new IllegalArgumentException();
    }

    if (product.name.isEmpty()) {
      throw new IllegalArgumentException();
    }

    if (product.price < 0) {
      throw new IllegalArgumentException();
    }

    if (product.id != productDiscount.id) {
      throw new IllegalArgumentException();
    }

    int discountPrice = getDiscountPrice(product.price);

    int tmp;
    if (productDiscount.canDiscount) {
      tmp = totalPrice + discountPrice;
    } else {
      tmp = totalPrice + product.price;
    }

    if (tmp <= 200_000) {
      totalPrice = tmp;
      discountProducts.add(product);
      return true;
    } else {
      return false;
    }
  }

  /**
   * 할인 가격 확인하기
   *
   * @param price 상품 가격
   * @return 할인 가격
   */
  static int getDiscountPrice(int price) {
    int discountPrice = price - 3000;
    if (discountPrice < 0) {
      discountPrice = 0;
    }
    return discountPrice;
  }

}

class Product {

  int id;
  String name;
  int price;
  boolean canDiscount;
}

class ProductDiscount {

  int id;
  boolean canDiscount;
}

class SummerDiscountManager {

  DiscountManager discountManager;

  boolean add(Product product) {
    if (product.id < 0) {
      throw new IllegalArgumentException();
    }
    if (product.name.isEmpty()) {
      throw new IllegalArgumentException();
    }

    int tmp;
    if (product.canDiscount) {
      tmp = discountManager.totalPrice + DiscountManager.getDiscountPrice(product.price);
    } else {
      tmp = discountManager.totalPrice + product.price;
    }

    if (tmp < 300_000) {
      discountManager.totalPrice = tmp;
      discountManager.discountProducts.add(product);
      return true;
    } else {
      return false;
    }
  }
}


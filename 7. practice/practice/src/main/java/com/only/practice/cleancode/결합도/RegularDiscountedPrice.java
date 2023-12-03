package com.only.practice.cleancode.결합도;

/**
 * Created by Gyuhwan
 */
public class RegularDiscountedPrice {

  private static final int MIN_AMOUNT = 0;
  private static final int DISCOUNT_AMOUNT = 4000;
  final int amount;

  public RegularDiscountedPrice(final RegularPrice price) {
    int discountedAmount = price.amount - DISCOUNT_AMOUNT;
    if (discountedAmount < MIN_AMOUNT) {
      discountedAmount = MIN_AMOUNT;
    }
    amount = discountedAmount;
  }

}

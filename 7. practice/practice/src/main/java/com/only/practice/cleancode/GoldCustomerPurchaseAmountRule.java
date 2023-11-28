package com.only.practice.cleancode;

/**
 * Created by Gyuhwan
 */
class GoldCustomerPurchaseAmountRule implements ExcellentCustomerRule {

  @Override
  public boolean ok(PurchaseHistory history) {
    return 1_000_000 <= history.totalAmount;
  }
}

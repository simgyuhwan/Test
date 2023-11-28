package com.only.practice.cleancode;

/**
 * Created by Gyuhwan
 */
class PurchaseFrequencyRule implements ExcellentCustomerRule {

  @Override
  public boolean ok(PurchaseHistory history) {
    return 10 <= history.purchaseFrequencyPerMonth;
  }
}

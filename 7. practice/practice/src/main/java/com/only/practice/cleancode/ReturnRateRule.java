package com.only.practice.cleancode;

/**
 * Created by Gyuhwan
 */
class ReturnRateRule implements ExcellentCustomerRule {

  @Override
  public boolean ok(PurchaseHistory history) {
    return history.returnRate <= 0.001;
  }
}


package com.only.practice.cleancode;

/**
 * Created by Gyuhwan
 */
class GoldCustomerPolicy {

  private final ExcellentCustomerPolicy policy;

  public GoldCustomerPolicy() {
    policy = new ExcellentCustomerPolicy();
    policy.add(new GoldCustomerPurchaseAmountRule());
    policy.add(new PurchaseFrequencyRule());
    policy.add(new ReturnRateRule());
  }

  boolean complyWithAll(final PurchaseHistory history) {
    return policy.complyWithAll(history);
  }
}

package com.only.practice.cleancode.다중중첩;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gyuhwan
 */
class ExcellentCustomerPolicy {

  private final Set<ExcellentCustomerRule> rules;

  public ExcellentCustomerPolicy() {
    this.rules = new HashSet<>();
  }

  /**
   * 규칙 추가
   *
   * @param rule 규칙
   */
  void add(final ExcellentCustomerRule rule) {
    rules.add(rule);
  }

  /**
   * @param history 구매 이력
   * @return 규칙을 모두 만족하는 경우 true
   */
  boolean complyWithAll(final PurchaseHistory history) {
    for (ExcellentCustomerRule each : rules) {
      if (!each.ok(history)) {
        return false;
      }
    }
    return true;
  }
}

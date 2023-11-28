package com.only.practice.cleancode;

/**
 * Created by Gyuhwan
 */
interface ExcellentCustomerRule {

  /**
   * @param history 구매이력
   * @return 조건을 만족하는 경우 true
   */
  boolean ok(final PurchaseHistory history);

}

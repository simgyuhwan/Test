package com.only.practice.cleancode.자료형확인;

/**
 * Created by Gyuhwan
 */
class RegularRates implements HotelRates {

  @Override
  public Money fee() {
    return new Money(70_000);
  }

  @Override
  public Money busySeasonFee() {
    return fee().add(new Money(30_000));
  }
}

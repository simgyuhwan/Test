package com.practice.jpa.reservation;

/**
 * NoneDiscountPolicy.java
 * 할인 정책 없음
 *
 * @author sgh
 * @since 2023.06.05
 */
public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }

}

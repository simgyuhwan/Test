package com.practice.jpa.reservation;

/**
 * DiscountPolicy.java
 * 할인 정책
 *
 * @author sgh
 * @since 2023.06.05
 */
public interface DiscountPolicy {
	/**
	 * 할인 금액 계산
	 *
	 * @param screening 상영 영화
	 * @return 할인 금액
	 */
	Money calculateDiscountAmount(Screening screening);
}

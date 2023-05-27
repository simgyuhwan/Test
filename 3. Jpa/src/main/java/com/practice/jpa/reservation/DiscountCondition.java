package com.practice.jpa.reservation;

public interface DiscountCondition {
	boolean isSatisfiedBy(Screening screening);
}

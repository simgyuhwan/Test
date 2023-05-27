package com.practice.jpa.reservation;

import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy{
	private double percent;

	public PercentDiscountPolicy(double percent, List<DiscountCondition> conditionList) {
		super(conditionList);
		this.percent = percent;
	}

	@Override
	protected Money getDiscountAmount(Screening screening) {
		return screening.getMovieFee().times(percent);
	}
}

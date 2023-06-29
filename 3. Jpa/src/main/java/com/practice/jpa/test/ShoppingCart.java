package com.practice.jpa.test;

import java.time.LocalDate;
import java.util.Calendar;

public class ShoppingCart {
	private boolean readyForDelivery = false;

	public void markAsReadyForDelivery(LocalDate estimatedDayOfDelivery) {

	}

	public void markAsReadyForDelivery(Calendar estimatedDayOfDelivery) {
		this.readyForDelivery = true;

	}

	/**
	 * 클래스의 상태를 관찰할 수 있도록 메서드를 추가한다. 배송 준비 완료 상태로 변경되었는지 확인하기 위해
	 * 확인 메서드를 추가했다.
	 */
	public boolean isReadyForDelivery() {
		return readyForDelivery;
	}
}

package com.practice.jpa.test;

import java.time.LocalDate;
import java.util.List;

public class PaidShoppingCartsBatch {
	private ShoppingCartRepository db;
	private DeliveryCenter deliveryCenter;
	private CustomerNotifier notifier;
	private SAP sap;

	public PaidShoppingCartsBatch(ShoppingCartRepository db, DeliveryCenter deliveryCenter,
		CustomerNotifier notifier, SAP sap) {
		this.db = db;
		this.deliveryCenter = deliveryCenter;
		this.notifier = notifier;
		this.sap = sap;
	}

	public void processAll() {
		List<ShoppingCart> paidShoppingCarts = db.cartsPaidToday();

		for (ShoppingCart cart : paidShoppingCarts) {
			// 배송 시스템에 카트를 전달하고 배송 예정일을 반환
			LocalDate estimatedDayOfDelivery = deliveryCenter.deliver(cart);

			// 배송 준비를 완료 상태로 만들고 DB에 영속화 한다.
			cart.markAsReadyForDelivery(estimatedDayOfDelivery);
			db.persist(cart);

			// 고객에게 알림을 보낸다
			notifier.sendEstimatedDeliveryNotification(cart);

			// SAP에 알린다.
			sap.cartReadyForDelivery(cart);
		}
	}
}

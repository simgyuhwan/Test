package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 핵심은 인프라와 도메인을 분리하는 것이다. 인터페이스를 이용해서 단순하게 코드를 작성할 수 있다는 것을 의미하지 않는다.
 */
@ExtendWith(MockitoExtension.class)
class PaidShoppingCartsBatchTest {

	// 테스트할 포트
	@Mock
	ShoppingCartRepository db;

	// 테스트할 포트
	@Mock
	private DeliveryCenter deliveryCenter;

	// 테스트할 포트
	@Mock
	private CustomerNotifier notifier;

	// 테스트할 포트
	@Mock
	private SAP sap;

	@InjectMocks
	private PaidShoppingCartsBatch batch;

	/**
	 * 	ShoppingCart 는 Entity 이기 때문에 모의할 필요가 없다. 단언을 위해 스파이로 만든다.
	 * 	엔티티가 복잡하지 않다면 모의하지 말자.
 	 */
	@Test
	void theWholeProcessHappens() {
		ShoppingCart someCart = new ShoppingCart();

		assertThat(someCart.isReadyForDelivery()).isFalse();

		Calendar someDate = Calendar.getInstance();
		when(db.cartsPaidToday()).thenReturn(List.of(someCart));
		when(deliveryCenter.deliver(someCart)).thenReturn(someDate);

		batch.processAll();

		// 흐름 검증
		verify(deliveryCenter).deliver(someCart);
		verify(notifier).sendEstimatedDeliveryNotification(someCart);
		verify(db).persist(someCart);
		verify(sap).cartReadyForDelivery(someCart);

		// 기존 Spy로 사용하던 someCart를 실제 객체로 만든 다음 객체 상태를 확인하는 메서드를 추가한다.
		assertThat(someCart.isReadyForDelivery()).isTrue();
	}
}
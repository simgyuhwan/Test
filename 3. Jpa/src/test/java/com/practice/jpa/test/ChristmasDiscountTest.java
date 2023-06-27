package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * ChristmasDiscount 내부의 함수는 LocalDate.now(); 를 직접 호출한다. 외부에서 이 날짜를 제어하고 싶다면
 * 	날짜를 캡슐화하여 모키토의 기능을 이용하자.
 *
 * 	* 소유하지 않는 것은 모의하지 말자.
 * 	Clock 처럼 추상화 클래스를 만들어서 모의하자. 하지만 이렇게 할 경우, Clock이 추상화하고 있는 외부 라이브러리의 변화에는
 * 	테스트 실패가 이루어지지 않는다. 이때는 통합 테스트를 활용하자. (테스트 분리)
 *
 */
@ExtendWith(MockitoExtension.class)
class ChristmasDiscountTest {
	@Mock
	private Clock clock;

	@InjectMocks
	private final ChristmasDiscount cd = new ChristmasDiscount(clock);

	@Test
	public void christmas() {
		LocalDate christmas = LocalDate.of(2015, Month.DECEMBER, 25);
		when(clock.now()).thenReturn(christmas);

		double finalValue = cd.applyDiscount(100.0);
		assertThat(finalValue).isCloseTo(85.0, offset(0.001));
	}

	@Test
	public void notChristmas() {
		LocalDate notChristmas = LocalDate.of(2015, Month.DECEMBER, 26);
		when(clock.now()).thenReturn(notChristmas);

		double finalValue = cd.applyDiscount(100.0);
		assertThat(finalValue).isCloseTo(100.0, offset(0.001));
	}
}
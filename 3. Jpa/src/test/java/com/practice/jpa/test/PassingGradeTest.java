package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.FloatRange;

class PassingGradeTest {

	private final PassingGrade pg = new PassingGrade();

	/**
	 * fail 속성 기반 테스트
	 *
	 * grade 는 5 이상이어야 합격이다.
	 * 1 ~ 4까지의 값을 임의로 주입
	 *
	 * @param grade
	 */
	@Property // 속성 기반 정의
	void fail(
		@ForAll // 값을 생성하는 매개변수에는 필요한 애너테이션
		@FloatRange(min = 1f, max = 5f, maxIncluded = false) // 생성할 값의 범위 정의
		float grade
	) {
		assertThat(pg.passed(grade)).isFalse();
	}

	@Property
	void pass(
		@ForAll
		@FloatRange(min = 5f, max = 10f)
		float grade
	) {
		assertThat(pg.passed(grade)).isTrue();
	}

	@Property
	void invalid(
		@ForAll("invalidGrades")
		float grade
	) {
		assertThatThrownBy(() -> {
			pg.passed(grade);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	/**
	 * @FloatRange로는 `grade < 1 || grade > 10` 을 표현할 수 없다.
	 * 따로 함수를 제공해서 표현할 수 있는데.
	 * @Provide 애너테이션이 달린 메서드를 사용이 가능하다.
	 */
	@Provide
	private Arbitrary<Float> invalidGrades() {
		return Arbitraries.oneOf(
			Arbitraries.floats().lessThan(1f),
			Arbitraries.floats().greaterThan(10f)
		);
	}
}
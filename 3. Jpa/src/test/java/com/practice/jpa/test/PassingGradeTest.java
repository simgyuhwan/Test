package com.practice.jpa.test;

import static java.util.Comparator.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.FloatRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

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

	/**
	 * PassingGrade.unique 함수는 배열의 중복 값을 제거하고
	 * 오름차순으로 바꿔준다. 이러한 속성을 확인하는 테스트
	 *
	 */
	@Property
	void unique(
		@ForAll
		@Size(value = 100)
		List<@IntRange(min = 1, max = 20) Integer>
		numbers
	) {
		int[] doubles = convertListToArray(numbers);
		int[] result = PassingGrade.unique(doubles);

		assertThat(result)
			.contains(doubles)
			.doesNotHaveDuplicates()
			.isSortedAccordingTo(reverseOrder());
	}

	private int[] convertListToArray(List<Integer> numbers) {
		return numbers
			.stream()
			.mapToInt(x -> x)
			.toArray();
	}

	@Property
	void indexOfShouldFindFirstValue(
		@ForAll
		@Size(value = 100) List<@IntRange(min = -1000, max = 1000) Integer> numbers,
		@ForAll
		@IntRange(min = 1001, max = 2000) int value,
		@ForAll
		@IntRange(max = 99) int indexToAddElement,
		@ForAll
		@IntRange(max = 99) int startIndex) {

		numbers.add(indexToAddElement, value);
		int[] array = convertListToArray(numbers);

		int expectedIndex = indexToAddElement >= startIndex ? indexToAddElement : -1;

		assertThat(ArrayUtils.indexOf(array, value, startIndex)).isEqualTo(expectedIndex);
	}

}
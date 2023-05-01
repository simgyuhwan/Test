package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CodingBatTest {

	@ParameterizedTest
	@MethodSource("generator")
	void testClumps(int[] nums, int expectedNoOfClumps) {
		assertThat(CodingBat.countClumps(nums)).isEqualTo(expectedNoOfClumps);
	}

	static Stream<Arguments> generator() {
		return Stream.of(
			of(new int[]{}, 0),
			of(null, 0),
			of(new int[]{1,2,2,2,1}, 2),
			of(new int[]{1}, 0)
		);
	}
}
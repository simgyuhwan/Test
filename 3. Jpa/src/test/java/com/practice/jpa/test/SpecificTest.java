package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpecificTest {

	@Test
	void simpleCase() {
		assertThat(Specific.substringsBetween("abcd", "a", "d")).isEqualTo(new String[]{"bc"});
	}

	@Test
	void manySubstrings() {
		assertThat(Specific.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[]{"bc", "bc"});
	}

	@Test
	void openAndCloseTagsThatAreLongerThan1Char() {
		assertThat(Specific.substringsBetween("aabcddaabfddaab", "aa","dd")).isEqualTo(new String[] {"bc", "bf"});
	}

	@ParameterizedTest
	@MethodSource("generator")
	void test(String originalStr, int size, String padString, String expectedStr) {
		assertThat(Specific.leftPad(originalStr, size, padString))
			.isEqualTo(expectedStr);
	}

	static Stream<Arguments> generator() {
		return Stream.of(
			of(null, 10, "-", null),
			of("", 5, "-", "-----"),
			of("abc", -1, "-", "abc"),
			of("abc", 5, "", "  abc"),
			of("abc", 5, "-", "--abc"),
			of("abc", 3, "-", "abc"),
			of("abc", 0, "-", "abc"),
			of("abc", 2, "-", "abc"),
			of("abc", 5, "--", "--abc"),
			of("abc", 5, "---", "--abc"),
			of("abc", 5, "-", "--abc")
		);
	}

}
package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
}
package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountWordsTest {

	@Test
	void twoWordsEndingWithS() {
		int words = new CountWords().count("dogs cats");
		assertThat(words).isEqualTo(2);
	}

	@Test
	void noWordsAtAll() {
		int words = new CountWords().count("dog cat");
		assertThat(words).isEqualTo(0);
	}

	@Test
	void wordsThatEndInR() {
		int words = new CountWords().count("car bar");
		assertThat(words).isEqualTo(2);
	}

}
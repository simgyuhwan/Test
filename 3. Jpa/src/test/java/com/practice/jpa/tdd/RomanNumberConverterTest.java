package com.practice.jpa.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RomanNumberConverterTest {

	@Test
	void shouldUnderstandSymbolI() {
		RomanNumberConverter roman = new RomanNumberConverter();
		int number = roman.converter("I");
		assertThat(number).isEqualTo(1);
	}

	@Test
	void shouldUnderstandSymbolV() {
		RomanNumberConverter roman = new RomanNumberConverter();
		int number = roman.converter("V");
		assertThat(number).isEqualTo(5);
	}

	@ParameterizedTest
	@CsvSource({"I,1", "V,5", "X,10", "L,50", "C, 100", "D, 500", "M, 1000"})
	void shouldUnderstandOneCharNumbers(String romanNumber, int expectedNumberAfterConversion) {
		RomanNumberConverter roman = new RomanNumberConverter();
		int convertedNumber = roman.converter(romanNumber);
		assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
	}

}


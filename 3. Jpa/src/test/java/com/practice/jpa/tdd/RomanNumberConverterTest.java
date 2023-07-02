package com.practice.jpa.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RomanNumberConverterTest {

    @ParameterizedTest
    @CsvSource({"I,1", "V,5", "X,10", "L,50", "C, 100", "D, 500", "M, 1000"})
    void shouldUnderstandOneCharNumbers(String romanNumber, int expectedNumberAfterConversion) {
        RomanNumberConverter roman = new RomanNumberConverter();
        int convertedNumber = roman.converter(romanNumber);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

    // ver 1
    @Test
    void shouldUnderstandMultipleCharNumbers() {
        RomanNumberConverter roman = new RomanNumberConverter();
        int convertedNumber = roman.converter("II");
        assertThat(convertedNumber).isEqualTo(2);
    }

    // ver2
    @ParameterizedTest
    @CsvSource({"II, 2", "III, 3", "VI, 6", "XVIII, 18", "XXIII, 23", "DCCLXVI, 766"})
    void shouldUnderstandMultipleCharNumbers(String romanNumber,
        int expectedNumberAfterConversion) {
        RomanNumberConverter roman = new RomanNumberConverter();
        int convertedNumber = roman.converter(romanNumber);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }

    @ParameterizedTest
    @CsvSource({"IV,4", "XIV,14", "XL,40", "XLI,41", "CCXCIV,294"})
    void shouldUnderstandSubtractiveNotation(String romanNumber,
        int expectedNumberAfterConversion) {
        RomanNumberConverter roman = new RomanNumberConverter();
        int convertedNumber = roman.converter(romanNumber);
        assertThat(convertedNumber).isEqualTo(expectedNumberAfterConversion);
    }
}


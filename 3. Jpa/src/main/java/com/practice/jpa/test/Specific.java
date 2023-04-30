package com.practice.jpa.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class Specific {
	private static String[] EMPTY_STRING_ARRAY;
	private static String SPACE = " ";

	public static String leftPad(final String str, final int size, String padStr) {
		if(str == null) return null;

		if(padStr == null || padStr.isEmpty()) {
			padStr = SPACE;
		}

		final int padLen = padStr.length();
		final int strLen = str.length();
		final int pads = size - strLen;

		if(pads <= 0) {
			return str;
		}

		if(pads == padLen) {
			return padStr.concat(str);
		}else if(pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		}else {
			final char[] padding = new char[pads];
			final char[] padChars = padStr.toCharArray();

			for(int i=0; i<pads; i++) {
				padding[i] = padChars[i % padLen];
			}

			return new String(padding).concat(str);
		}
	}

	public static String[] substringsBetween(final String str, final String open, final String close) {
		if(isEmpty(str) || isEmpty(open) || isEmpty(close)) {
			return null;
		}

		int strLen = str.length();
		if(strLen == 0) {
			return EMPTY_STRING_ARRAY;
		}

		int closeLen = close.length();
		int openLen = open.length();
		List<String> list = new ArrayList<>();
		int pos = 0;

		while(pos < strLen - closeLen) {
			int start = str.indexOf(open, pos);
			if(start < 0) {
				break;
			}

			start += openLen;

			int end = str.indexOf(close, start);
			if(end < 0) {
				break;
			}

			list.add(str.substring(start, end));
			pos = end + closeLen;
		}

		if(list.isEmpty()) {
			return null;
		}
		EMPTY_STRING_ARRAY = new String[list.size()];
		return list.toArray(EMPTY_STRING_ARRAY);

	}

	private static boolean isEmpty(String str) {
		return !StringUtils.hasText(str);
	}
}

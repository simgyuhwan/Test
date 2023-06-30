package com.practice.jpa.tdd;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberConverter {

	private static Map<String, Integer> table = new HashMap<>() {{
		put("I", 1);
		put("V", 5);
		put("X", 10);
		put("L", 50);
		put("C", 100);
		put("D", 500);
		put("M", 1000);
	}};

	public int converter(String numberInRoman) {
		return table.get(numberInRoman);
	}
}

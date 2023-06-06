package com.practice.jpa.test;

import java.util.Iterator;
import java.util.TreeSet;

public class PassingGrade {
	public boolean passed(float grade) {
		if(grade < 1.0 || grade > 10.0) {
			throw new IllegalArgumentException();
		}
		return grade >= 5.0;
	}

	public static int[] unique(int[] data) {
		TreeSet<Integer> values = new TreeSet<>();
		for (int d : data) {
			values.add(d);
		}

		final int count = values.size();
		final int[] out = new int[count];

		Iterator<Integer> iterator = values.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			out[count - ++i] = iterator.next();
		}
		return out;
	}
}

package com.practice.jpa.test;

public class PassingGrade {
	public boolean passed(float grade) {
		if(grade < 1.0 || grade > 10.0) {
			throw new IllegalArgumentException();
		}
		return grade >= 5.0;
	}
}

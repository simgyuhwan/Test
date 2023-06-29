package com.practice.jpa.test;

import java.time.LocalDate;

public class Installment {
	private LocalDate nextInstallmentDueDate;
	private double amountPerInstallment;

	public Installment(LocalDate nextInstallmentDueDate, double amountPerInstallment) {
		this.nextInstallmentDueDate = nextInstallmentDueDate;
		this.amountPerInstallment = amountPerInstallment;
	}

	public double getValue() {
		return amountPerInstallment;
	}

	public LocalDate getDate() {
		return nextInstallmentDueDate;
	}
}

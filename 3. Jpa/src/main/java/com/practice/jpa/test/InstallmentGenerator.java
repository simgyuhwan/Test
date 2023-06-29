package com.practice.jpa.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator {
	private InstallmentRepository repository;

	public InstallmentGenerator(InstallmentRepository repository) {
		this.repository = repository;
	}

	/**
	 * cart에는 금액이 지정되어 있고 numberOfInstallments 는 할부개월이 정의되어 있다.
	 */
	public List<Installment> generateInstallments(ShoppingCart cart, int numberOfInstallments) {
		List<Installment> generatedInstallments = new ArrayList<>();
		// 현재일 기준
		LocalDate nextInstallmentDueDate = LocalDate.now();

		// cart 금액에서 할부개월을 나눈다.(매달 지급할 금액)
		double amountPerInstallment = cart.getValue() / numberOfInstallments;

		for(int i = 1; i <= numberOfInstallments; i++) {
			// 현재일 기준으로 한달씩 갱신
			nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1);

			// 할부 금액과 납부일 저장
			Installment newInstallment = new Installment(nextInstallmentDueDate, amountPerInstallment);
			repository.persist(newInstallment);

			generatedInstallments.add(newInstallment);
		}

		return generatedInstallments;
	}
}

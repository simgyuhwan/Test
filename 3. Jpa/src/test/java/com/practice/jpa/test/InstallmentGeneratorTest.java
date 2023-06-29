package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InstallmentGeneratorTest {

	@Mock
	private InstallmentRepository repository;

	@Test
	void checkInstallments() {
		InstallmentGenerator generator = new InstallmentGenerator(repository);
		ShoppingCart cart = new ShoppingCart(100.0);

		// 반환값을 변경
		List<Installment> allInstallments = generator.generateInstallments(cart, 10);

		// 금액이 10 분할 되어있는지 확인
		assertThat(allInstallments).hasSize(10).allMatch(i -> i.getValue() == 10);

		// 할부 개월이 정확히 등록되었는지 확인
		for (int month = 1; month <= 10; month++) {
			final LocalDate dueDate = LocalDate.now().plusMonths(month);
			assertThat(allInstallments).anyMatch(i -> i.getDate().equals(dueDate));
		}
	}

}
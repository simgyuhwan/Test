package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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

		// void 메서드일 때
		generator.generateInstallments(cart, 10);

		// Captor를 사용해서 void 중간에 사용된 인스턴스를 가져온다.
		ArgumentCaptor<Installment> captor = ArgumentCaptor.forClass(Installment.class);
		verify(repository, times(10)).persist(captor.capture());
		List<Installment> allInstallments = captor.getAllValues();

		// 금액이 10 분할 되어있는지 확인
		assertThat(allInstallments).hasSize(10).allMatch(i -> i.getValue() == 10);

		// 할부 개월이 정확히 등록되었는지 확인
		for (int month = 1; month <= 10; month++) {
			final LocalDate dueDate = LocalDate.now().plusMonths(month);
			assertThat(allInstallments).anyMatch(i -> i.getDate().equals(dueDate));
		}
	}

}
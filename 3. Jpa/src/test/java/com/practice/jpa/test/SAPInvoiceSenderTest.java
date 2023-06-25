package com.practice.jpa.test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SAPInvoiceSenderTest {
	@Mock
	private InvoiceFilter filter;

	@Mock
	private SAP sap;

	@InjectMocks
	private SAPInvoiceSender sender;

	@Test
	void sendToSap() throws SAPException {
		Invoice master = new Invoice("Master", 20);
		Invoice franck = new Invoice("Frank", 99);

		List<Invoice> invoices = Arrays.asList(master, franck);

		when(filter.lowValueInvoices()).thenReturn(invoices);

		sender.sendLowValuedInvoices();

		verify(sap, times(2 )).send(any(SapInvoice.class));
	}

	@Test
	void noLowValueInvoices() throws SAPException {
		List<Invoice> invoices = Collections.emptyList();
		when(filter.lowValueInvoices()).thenReturn(invoices);

		sender.sendLowValuedInvoices();

		verify(sap, never()).send(any(SapInvoice.class));
	}

	@Test
	void sendSapInvoiceToSap() throws SAPException {
		Invoice master = new Invoice("Master", 20);

		List<Invoice> invoices = List.of(master);
		when(filter.lowValueInvoices()).thenReturn(invoices);

		sender.sendLowValuedInvoices();

		verify(sap).send(any(SapInvoice.class));
	}

	// 인수 포획기 사용
	@ParameterizedTest
	@CsvSource({
		"master,ma",
		"M,X"
	})
	void sendToSapWithTheGeneratedId(String customer, String customerCode) throws SAPException {
		Invoice master = new Invoice(customer, 20);

		List<Invoice> invoices = List.of(master);
		when(filter.lowValueInvoices()).thenReturn(invoices);

		sender.sendLowValuedInvoices();

		ArgumentCaptor<SapInvoice> captor = ArgumentCaptor.forClass(SapInvoice.class);

		verify(sap).send(captor.capture());

		SapInvoice generatedSapInvoice = captor.getValue();

		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
		assertThat(generatedSapInvoice)
			.isEqualTo(new SapInvoice(customer, 20, date + customerCode));
	}

	@Test
	void returnFailedInvoices() throws SAPException {
		Invoice frank = new Invoice("Frank", 25);
		Invoice master = new Invoice("master", 20);
		Invoice steve = new Invoice("Steve", 94);

		List<Invoice> invoices = List.of(master, frank, steve);
		when(filter.lowValueInvoices()).thenReturn(invoices);

		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
		SapInvoice frankInvoice = new SapInvoice("Frank", 25, date + "Fr");

		lenient().doThrow(new SAPException()).when(sap).send(frankInvoice);

		List<Invoice> failedInvoices = sender.sendLowValuedInvoices();
		assertThat(failedInvoices).containsExactly(frank);

		SapInvoice masterInvoice = new SapInvoice("master", 20, date + "ma");
		verify(sap).send(masterInvoice);

		SapInvoice steveInvoice = new SapInvoice("Steve", 94, date + "St");
		verify(sap).send(steveInvoice);
	}

}
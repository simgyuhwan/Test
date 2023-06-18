package com.practice.jpa.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SAPInvoiceSender {
	private final InvoiceFilter filter;
	private final SAP sap;

	public SAPInvoiceSender(InvoiceFilter filter, SAP sap) {
		this.filter = filter;
		this.sap = sap;
	}

	public List<Invoice> sendLowValuedInvoices() {
		List<Invoice> failedInvoices = new ArrayList<>();

		List<Invoice> lowValuedInvoices = filter.lowValueInvoices();

		for(Invoice invoice: lowValuedInvoices) {
			SapInvoice sapInvoice = convert(invoice);

			try {
				sap.send(sapInvoice);
			} catch (SAPException e) {
				failedInvoices.add(invoice);
			}
		}
		return failedInvoices;
	}

	public SapInvoice convert(Invoice invoice) {
		String customer = invoice.getCustomer();
		Integer value = invoice.getValue();
		String sapId = generateId(invoice);

		return new SapInvoice(customer, value, sapId);
	}

	private String generateId(Invoice invoice) {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
		String customer = invoice.getCustomer();

		return date + (customer.length() >= 2 ? customer.substring(0, 2) : "X");
	}
}

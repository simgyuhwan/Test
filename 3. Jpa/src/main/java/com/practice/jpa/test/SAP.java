package com.practice.jpa.test;

public interface SAP {
	void send(SapInvoice invoice) throws SAPException;

	void cartReadyForDelivery(ShoppingCart cart);
}

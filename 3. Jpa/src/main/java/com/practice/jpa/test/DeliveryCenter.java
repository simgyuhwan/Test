package com.practice.jpa.test;

import java.time.LocalDate;
import java.util.Calendar;

public interface DeliveryCenter {
	// LocalDate deliver(ShoppingCart cart);

	Calendar deliver(ShoppingCart cart);
}

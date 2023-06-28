package com.practice.jpa.test;

import java.time.LocalDate;

public interface DeliveryCenter {
	LocalDate deliver(ShoppingCart cart);
}

package com.practice.jpa.test;

import java.util.List;

public interface ShoppingCartRepository {
	public List<ShoppingCart> cartsPaidToday();

	public void persist(ShoppingCart cart);
}

package com.test.cafekiosk.unit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.test.cafekiosk.unit.beverage.Beverage;
import com.test.cafekiosk.unit.order.Order;

import lombok.Getter;

@Getter
public class CafeKiosk {
	private final List<Beverage> beverages = new ArrayList<>();

	public void add(Beverage beverage) {
		beverages.add(beverage);
	}

	public int calculateTotalPrice() {
		return beverages.stream().mapToInt(Beverage::getPrice).sum();
	}

	public void remove(Beverage beverage) {
		beverages.remove(beverage);
	}

	public void clear() {
		beverages.clear();
	}

	public Order createOrder() {
		return new Order(LocalDateTime.now(), beverages);
	}
}

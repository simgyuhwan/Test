package com.test.cafekiosk.unit.order;

import java.time.LocalDateTime;
import java.util.List;

import com.test.cafekiosk.unit.beverage.Beverage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Order {

	private final LocalDateTime orderDateTime;
	private final List<Beverage> beverages;
}

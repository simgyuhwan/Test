package com.only.practice.locktest.낙관적락;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Gyuhwan
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Long availableStock;

  @Version
  private Long version;

  public Stock(String name, Long availableStock) {
    this.name = name;
    this.availableStock = availableStock;
  }

  public static Stock createStock(String name, Long availableStock) {
    return new Stock(name, availableStock);
  }

  public void decrease(Long pickingCount) {
    validateStockCount(pickingCount);
    availableStock -= pickingCount;
  }

  private void validateStockCount(Long pikingCount) {
    if (pikingCount > availableStock) {
      throw new IllegalArgumentException();
    }
  }
}

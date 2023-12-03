package com.only.practice.locktest.낙관적락;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Gyuhwan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {

  private String name;
  private Long availableStock;

  public Stock toStock() {
    return Stock.createStock(name, availableStock);
  }
}

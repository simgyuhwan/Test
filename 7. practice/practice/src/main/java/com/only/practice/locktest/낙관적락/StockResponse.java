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
public class StockResponse {

  private String name;
  private Long availableStock;

  public static StockResponse toResponse(Stock stock) {
    return new StockResponse(stock.getName(), stock.getAvailableStock());
  }
}

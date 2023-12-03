package com.only.practice.locktest.낙관적락;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Gyuhwan
 */
@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository stockRepository;

  @Transactional
  public StockResponse createStock(StockRequest request) {
    Stock stock = request.toStock();
    stockRepository.save(stock);
    return StockResponse.toResponse(stock);
  }

  @Transactional
  public void decrease(Long stockId, Long pickingCount) {
    Stock stock = stockRepository.findById(stockId)
        .orElseThrow(IllegalStateException::new);

    stock.decrease(pickingCount);
  }
}

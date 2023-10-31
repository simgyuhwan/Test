package com.only.practice.locktest.낙관적락;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gyuhwan
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

}

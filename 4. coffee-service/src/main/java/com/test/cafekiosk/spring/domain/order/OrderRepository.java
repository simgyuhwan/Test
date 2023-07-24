package com.test.cafekiosk.spring.domain.order;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.registeredDateTime >= :startDateTime "
        + "AND o.registeredDateTime < :endDateTime "
        + "AND o.orderStatus = :orderStatus")
    List<Order> findOrdersBy(@Param("startDateTime") LocalDateTime startDateTime,
        @Param("endDateTime") LocalDateTime endDateTime,
        @Param("orderStatus") OrderStatus orderStatus);
}

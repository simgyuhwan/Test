package com.practice.jpa.repository.joined;

import com.practice.jpa.Inheritence.joined.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * SellerRepository.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByName(String name);
}

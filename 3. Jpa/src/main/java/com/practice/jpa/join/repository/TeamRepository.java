package com.practice.jpa.join.repository;

import com.practice.jpa.join.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeamRepository.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}

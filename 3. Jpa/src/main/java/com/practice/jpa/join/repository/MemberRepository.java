package com.practice.jpa.join.repository;

import com.practice.jpa.join.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * MemberRepository.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllMembers();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();
}

package com.kr.board.infra.repository;

import com.kr.board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmailOrNickname(String email, String nickname);


    boolean existsByEmailAndPassword(String email, String password);
}

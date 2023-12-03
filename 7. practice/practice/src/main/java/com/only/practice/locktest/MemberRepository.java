package com.only.practice.locktest;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gyuhwan
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

}

package com.only.practice.locktest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Gyuhwan
 */
@Service
@RequiredArgsConstructor
public class MemberTestService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long createAndMemberCnt(String name) {
    Member member = new Member(name);
    memberRepository.save(member);
    return memberRepository.count();
  }

}

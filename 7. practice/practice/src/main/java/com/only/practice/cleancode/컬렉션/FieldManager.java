package com.only.practice.cleancode.컬렉션;

import java.util.List;

/**
 * Created by Gyuhwan
 */
public class FieldManager {

  private final int MAX_MEMBER_COUNT = 10;

  void addMember(List<Member> members, Member newMember) {
    if (members.stream().anyMatch(member -> member.id == newMember.id)) {
      throw new RuntimeException("이미 존재하는 맴버입니다.");
    }

    if (members.size() == MAX_MEMBER_COUNT) {
      throw new RuntimeException("이 이상 맴버를 추가할 수 없습니다.");
    }

    members.add(newMember);
  }

  boolean partyIsAlive(List<Member> members) {
    return members.stream().anyMatch(member -> member.isAlive());
  }

}


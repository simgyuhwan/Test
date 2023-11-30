package com.only.practice.cleancode.컬렉션;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gyuhwan
 */
class Party {

  static final int MAX_MEMBER_COUNT = 4;
  private final List<Member> members;

  public Party() {
    this.members = new ArrayList<>();
  }

  private Party(List<Member> members) {
    this.members = members;
  }

  /**
   * 맴버 추가하기
   *
   * @param newMember 추가하고 싶은 맴버
   * @return 맴버를 추가한 파티
   */
  Party addMember(final Member newMember) {
    if (exists(newMember)) {
      throw new RuntimeException("이미 파티에 참가되어 있습니다.");
    }

    if (isFull()) {
      throw new RuntimeException("이 이상 맴버를 추가할 수 없습니다.");
    }

    List<Member> adding = new ArrayList<>(members);
    adding.add(newMember);
    return new Party(adding);
  }

  /**
   * @return 파티 맴버가 1명이라도 살아 있으면 true를 리턴
   */
  boolean isAlive() {
    return members.stream().anyMatch(Member::isAlive);
  }

  /**
   * @return 파티 인원이 최대일 때 true를 리턴
   */
  private boolean isFull() {
    return members.size() == MAX_MEMBER_COUNT;
  }

  /**
   * @param newMember 파티에 소속되어 있는지 확인하고 싶은 맴버
   * @return 이미 소속되어 있는 경우 true를 리턴
   */
  private boolean exists(final Member newMember) {
    return members.stream().anyMatch(member -> member.id == newMember.id);
  }

  List<Member> members() {
    return Collections.unmodifiableList(members);
  }
}

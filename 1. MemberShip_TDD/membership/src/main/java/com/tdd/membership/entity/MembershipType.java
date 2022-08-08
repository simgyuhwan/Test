package com.tdd.membership.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MembershipType {
    NAVER("네이버"),
    LINE("라인"),
    KAKAO("카카오");

    final private String companyName;
}

package com.kr.board.domain.member.factory;

import com.kr.board.domain.member.entity.Member;

public class MemberFactory {

    private static String email = "test@email.com";
    private static String nickname = "test";
    private static String password = "password";

    public static Member createMember() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}

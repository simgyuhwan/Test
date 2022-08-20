package com.kr.board.domain.member.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorResult{
    DUPLICATED_MEMBER_REGISTER(HttpStatus.BAD_REQUEST, "Duplicate email value.");

    private final HttpStatus httpStatus;
    private final String message;

}

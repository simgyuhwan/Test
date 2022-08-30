package com.kr.board.domain.member.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LoginErrorResult {
    NO_MEMBER_INFORMATION_EXCEPTION(HttpStatus.BAD_REQUEST, "Please check your ID or password."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

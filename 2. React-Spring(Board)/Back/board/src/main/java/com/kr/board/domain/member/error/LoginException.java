package com.kr.board.domain.member.error;

import lombok.Getter;

@Getter
public class LoginException extends RuntimeException{
    private LoginErrorResult loginErrorResult;

    private LoginException(LoginErrorResult loginErrorResult) {
        this.loginErrorResult = loginErrorResult;
    }

    private LoginException() {}

    public static LoginException of(final LoginErrorResult loginErrorResult) {
        return new LoginException(loginErrorResult);
    }
}

package com.kr.board.domain.posts.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorResult {
    INCORRECT_REGISTRATION_POST(HttpStatus.BAD_REQUEST, "Invalid post information"),;

    private final HttpStatus status;
    private final String message;
}

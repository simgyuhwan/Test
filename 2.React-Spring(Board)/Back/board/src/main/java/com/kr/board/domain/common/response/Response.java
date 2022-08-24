package com.kr.board.domain.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {

    private boolean success;
    private Result result;

    public static Response success(){
        return new Response(true, null);
    }

    public static <T> Response success(T data) {
        return new Response(true, new Success<T>(data));
    }

    public static <T> Response failure(String message) {
        return new Response(false, new Failure(message));
    }
}

package com.kr.board.domain.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Failure<T> implements Result{
    private String message;
    private T data;

    public Failure(String message) {
        this.message = message;
    }

}

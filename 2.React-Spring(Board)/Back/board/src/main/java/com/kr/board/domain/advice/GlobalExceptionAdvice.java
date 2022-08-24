package com.kr.board.domain.advice;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response MemberException(MemberException memberException) {
        MemberErrorResult memberErrorResult = memberException.getMemberErrorResult();
        log.info("error : {} ", memberErrorResult.getMessage());
        return Response.failure(memberErrorResult.getMessage());
    }
}

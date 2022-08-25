package com.kr.board.domain.advice;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.controller.MemberController;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kr.board.domain.member.error.MemberErrorResult.INCORRECT_REGISTRATION_INFORMATION;

@Slf4j
@RestControllerAdvice(basePackageClasses = {MemberController.class})
public class MemberExceptionAdvice {

    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response MemberException(MemberException e) {
        MemberErrorResult memberError = e.getMemberErrorResult();
        log.info("error : {} ", memberError.getMessage());
        return Response.failure(memberError.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response SignUpArgumentNotValidException(MethodArgumentNotValidException e){
        log.info("Signup field error : {}", e.getFieldError()
                .getField());
        return Response.failure(INCORRECT_REGISTRATION_INFORMATION
                .getMessage());
    }

}

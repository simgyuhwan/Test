package com.kr.board.infra.config.advice;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.controller.MemberController;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kr.board.domain.member.error.MemberErrorResult.INCORRECT_REGISTRATION_INFORMATION;

@Slf4j
@RestControllerAdvice(basePackageClasses = {MemberController.class})
public class MemberExceptionAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<Response> MemberException(MemberException e) {
        MemberErrorResult memberError = e.getMemberErrorResult();
        log.error("member error : {} ", memberError.getMessage());
        return ResponseEntity.status(memberError.getHttpStatus())
                .body(Response.failure(memberError.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response SignUpArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Signup field error : {}", e.getFieldError()
                .getField());
        return Response.failure(INCORRECT_REGISTRATION_INFORMATION
                .getMessage());
    }

}

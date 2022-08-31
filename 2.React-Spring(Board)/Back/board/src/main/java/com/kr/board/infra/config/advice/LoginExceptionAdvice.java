package com.kr.board.infra.config.advice;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.controller.LoginController;
import com.kr.board.domain.member.error.LoginErrorResult;
import com.kr.board.domain.member.error.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackageClasses = {LoginController.class})
public class LoginExceptionAdvice {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Response> LoginException(LoginException e) {
        LoginErrorResult loginError = e.getLoginErrorResult();
        log.error("login error : {}", loginError.getMessage());
        return ResponseEntity.status(loginError.getHttpStatus())
                .body(Response.failure(loginError.getMessage()));
    }
}

package com.kr.board.infra.config.advice;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.posts.controller.PostController;
import com.kr.board.domain.posts.error.PostErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kr.board.domain.posts.error.PostErrorResult.*;

@Slf4j
@RestControllerAdvice(basePackageClasses = {PostController.class})
public class PostExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response postRegisterArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Post Register field error : {}", e.getFieldError()
                .getField());
        return Response.failure(INCORRECT_REGISTRATION_POST
                .getMessage());
    }

}

package com.kr.board.infra.config.advice;

import com.kr.board.domain.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> entityNotFoundException(EntityNotFoundException e) {
        log.info("entity error : {}, what1 : {} , what2 : {}", e.getMessage(), e.getCause(),e.getLocalizedMessage());
        return ResponseEntity.badRequest()
                .body(Response.failure("Invalid approach."));
    }
}

package com.kr.board.domain.member.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.dto.LoginRequest;
import com.kr.board.domain.member.serivce.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        loginService.login(loginRequest);
        return ResponseEntity.ok().build();
    }
}

package com.kr.board.domain.member.controller;

import com.google.gson.Gson;
import com.kr.board.domain.member.dto.request.LoginRequest;
import com.kr.board.domain.member.error.LoginException;
import com.kr.board.domain.member.serivce.LoginService;
import com.kr.board.infra.config.advice.LoginExceptionAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.kr.board.domain.member.error.LoginErrorResult.NO_MEMBER_INFORMATION_EXCEPTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private String url = "/api/v1/login";
    private String email = "test@email.com";
    private String password = "password!";

    private MockMvc mockMvc;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController target;

    private Gson gson;

    @BeforeEach
    void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(LoginExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("로그인시 잘못된 등록 정보로 인한 메시지 반환")
    void LoginRequestFailedDueToInvalidInfoTest() throws Exception {
        // given
        doThrow(LoginException.of(NO_MEMBER_INFORMATION_EXCEPTION))
                .when(loginService)
                .login(any(LoginRequest.class));

        // when, then
        mockMvc.perform(post(url)
            .contentType(APPLICATION_JSON)
            .content(gson.toJson(createLoginRequestDTO())))
            .andExpect(jsonPath("$..['message']")
                    .value(NO_MEMBER_INFORMATION_EXCEPTION.getMessage()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccessTest() throws Exception{
        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(gson.toJson(createLoginRequestDTO())))
                .andExpect(status().isOk());
    }

    private LoginRequest createLoginRequestDTO() {
        return LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}

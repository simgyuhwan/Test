package com.kr.board.domain.member.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kr.board.domain.advice.GlobalExceptionAdvice;
import com.kr.board.domain.common.response.Failure;
import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.domain.member.serivce.MemberService;
import net.minidev.json.JSONObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static com.kr.board.domain.member.error.MemberErrorResult.DUPLICATED_MEMBER_REGISTER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    private final String url = "/api/v1/members";
    private final String email = "test@email.com";
    private final String nickname = "user";
    private final String password = "password!";

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController target;

    private MockMvc mockMvc;

    private Gson gson;

    @BeforeEach
    void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(GlobalExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("회원가입시 중복된 값으로 인해 실패")
    void signUpDuplicatedEmailExceptionTest() throws Exception {
        //given
        doThrow(new MemberException(DUPLICATED_MEMBER_REGISTER))
                .when(memberService)
                .addMember(any(MemberRequest.class));

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .content(gson.toJson(createRequestDTO()))
                .contentType(MediaType.APPLICATION_JSON));

        String content = result.andReturn()
                .getResponse()
                .getContentAsString();

        //then
        result.andExpect(status().isBadRequest());

        assertThat(content.contains(DUPLICATED_MEMBER_REGISTER.getMessage()),
                is(true));
    }

    private MemberRequest createRequestDTO(){
        return MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

}

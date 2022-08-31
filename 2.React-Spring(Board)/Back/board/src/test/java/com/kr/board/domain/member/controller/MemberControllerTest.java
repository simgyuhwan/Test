package com.kr.board.domain.member.controller;

import com.google.gson.Gson;
import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.domain.member.serivce.MemberService;
import com.kr.board.infra.config.advice.MemberExceptionAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.kr.board.domain.member.error.MemberErrorResult.DUPLICATED_MEMBER_REGISTER;
import static com.kr.board.domain.member.error.MemberErrorResult.INCORRECT_REGISTRATION_INFORMATION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    private final String url = "/api/v1/members";
    private final String email = "test@email.com";
    private final String nickname = "user";
    private final String password = "password1@!";

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
                .setControllerAdvice(MemberExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("회원가입시 중복된 값으로 인해 실패")
    void signUpDuplicatedEmailExceptionTest() throws Exception {
        //given
        doThrow(MemberException.of(DUPLICATED_MEMBER_REGISTER))
                .when(memberService)
                .addMember(any(MemberRequest.class));

        //when,then
        mockMvc.perform(post(url)
                .content(gson.toJson(createRequestDTO()))
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(DUPLICATED_MEMBER_REGISTER.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("닉네임 공백 예외 발생")
    void nicknameNullExceptionTest() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname("")
                .email(email)
                .password(password)
                .build();
        //when
        mockMvc.perform(post(url)
                .content(gson.toJson(request))
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("닉네임 최소 길이 예외 발생")
    void nicknameMinimumLengthExceptionTest() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname("ni")
                .email(email)
                .password(password)
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("잘못된 이메일 양식 예외 발생")
    void invalidEmailExceptionsTest() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email("invalid")
                .password(password)
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("이메일 공백 예외 발생")
    void emailNullExceptionTest() throws Exception{
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email("")
                .password(password)
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 공백 예외 발생")
    void passwordNullException() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password("")
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 최소 길이 예외 발생")
    void passwordMinimumLengthExceptionTest() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password("1234as!")
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 최소 하나의 문자 미포함 예외 발생")
    void includeAtLeastOneCharacterTest() throws Exception{
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password("1234!!!!!!")
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 최소 하나의 숫자 미포함 예외 발생")
    void includeAtLeastOneNumberTest() throws Exception{
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password("abcd!!!!!!")
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("비밀번호 최소 하나의 특수 문자 미포함 예외 발생")
    void includeAtLeastOneSpecialCharactersTest() throws Exception {
        //given
        MemberRequest request = MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password("abcd1111")
                .build();
        //when
        mockMvc.perform(post(url)
                        .content(gson.toJson(request))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_INFORMATION
                                .getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원가입 성공")
    void signUpSuccessTest() throws Exception{
        //when, then
        mockMvc.perform(post(url)
                        .content(gson.toJson(createRequestDTO()))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비빌번호 변경시, 비밀번호 불일치 예외 발생")
    void passwordMismatchTest() throws Exception{

    }

    private MemberRequest createRequestDTO(){
        return MemberRequest.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }

    private Member createMember(){
        return Member.builder()
                .id(1L)
                .nickname(nickname)
                .password(password)
                .email(email)
                .build();
    }

}

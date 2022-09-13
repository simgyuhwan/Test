package com.kr.board.domain.member.service;

import com.kr.board.domain.member.dto.LoginRequest;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.error.LoginException;
import com.kr.board.domain.member.serivce.LoginService;
import com.kr.board.infra.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kr.board.domain.member.error.LoginErrorResult.NO_MEMBER_INFORMATION_EXCEPTION;
import static com.kr.board.domain.factory.member.MemberFactory.createMember;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    LoginService target;

    private Member member;

    @BeforeEach
    void init() {
        member = createMember();
    }

    @Test
    @DisplayName("로그인시 회원 정보 불일치 예외 발생")
    void noLoginMemberInformationException(){
        //given
        given(memberRepository.existsByEmailAndPassword("test@email.com", "password!")).willReturn(false);

        //when
        LoginException result = assertThrows(LoginException.class, ()-> {target.login(createLoginRequest());});

        //then
        assertThat(result.getLoginErrorResult(), is(equalTo(NO_MEMBER_INFORMATION_EXCEPTION)));
    }

    @Test
    @DisplayName("로그인 성공")
    void passwordMismatchException() {
        //given
        given(memberRepository.existsByEmailAndPassword("test@email.com", "password!")).willReturn(true);

        //when
        boolean result = target.login(createLoginRequest());

        //then
        assertThat(result, is(true));
    }

    private static LoginRequest createLoginRequest(){
        return LoginRequest.builder()
                .email("test@email.com")
                .password("password!")
                .build();
    }
}

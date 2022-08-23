package com.kr.board.domain.member.service;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.infra.repository.MemberRepository;
import com.kr.board.domain.member.serivce.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    private String email = "test@email.com";
    private String nickname = "test";
    private String password = "password";


    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService target;

    @Test
    @DisplayName("이메일, 닉네임 중복 검사")
    void duplicatedEmail() {
        // given
        MemberRequestDTO memberDto = MemberRequestDTO.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();

        given(memberRepository.existsByEmailOrNickname(email, nickname)).willReturn(true);

        // when
        MemberException result = assertThrows(MemberException.class, ()-> target.addMember(memberDto));

        // then
        assertThat(result.getMemberErrorResult(),is(equalTo(MemberErrorResult.DUPLICATED_MEMBER_REGISTER)));
    }



}

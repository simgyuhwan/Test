package com.kr.board.domain.member.service;

import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.infra.repository.MemberRepository;
import com.kr.board.domain.member.serivce.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("이메일, 닉네임 중복 예외 발생")
    void duplicatedEVerificationToMemberRegisterTest() {
        // given
        given(memberRepository.existsByEmailOrNickname(email, nickname)).willReturn(true);

        // when
        MemberException result = assertThrows(MemberException.class,
                ()-> target.addMember(createMemberRequestDTO()));

        // then
        assertThat(result.getMemberErrorResult(),is(equalTo(MemberErrorResult.DUPLICATED_MEMBER_REGISTER)));
    }

    @Test
    @DisplayName("회원 등록 성공")
    void successfulMembershipRegistrationTest() {
        //given
        Member member = createMember();
        given(memberRepository.existsByEmailOrNickname(email, nickname))
                .willReturn(false);
        when(memberRepository.save(ArgumentMatchers.any(Member.class)))
                .thenReturn(member);

        //when
        target.addMember(createMemberRequestDTO());

        // verify
        verify(memberRepository, times(1))
                .existsByEmailOrNickname(email,nickname);
        verify(memberRepository, times(1))
                .save(ArgumentMatchers.any(Member.class));
    }

    private Member createMember(){
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    private MemberRequest createMemberRequestDTO(){
        return MemberRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

}

package com.kr.board.domain.member.repository;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.infra.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class MemberRepositoryTest {

    private String email = "test@email.com";
    private String nickname = "test";
    private String password = "password";

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록")
    void memberRegisterTest(){

        // given, when
        Member result = memberRepository.save(createMember());

        // then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getNickname(), is(equalTo(nickname)));
        assertThat(result.getEmail(), is(equalTo(email)));
    }

    @Test
    @DisplayName("이메일를 통한 회원 조회")
    void duplicateMemberExceptionTest(){
        // given
        Member saveMember = memberRepository.save(createMember());

        // when
        Member result = memberRepository.findByEmail(saveMember.getEmail()).orElse(null);

        // then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getEmail(), is(equalTo(saveMember.getEmail())));
    }

    @Test
    @DisplayName("이메일 중복 확인")
    void checkEmailDuplicationTest(){
        //given
        Member saveMember = memberRepository.save(createMember());

        //when
        boolean result = memberRepository.existsByEmailOrNickname(email, "no nickname");

        //then
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("닉네임 중복 확인")
    void checkNicknameDuplicationTest(){
        //given
        Member saveMember = memberRepository.save(createMember());

        //when
        boolean result = memberRepository.existsByEmailOrNickname("no email", nickname);

        //then
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("닉네임 또는 이메일 중복 없음")
    void noNicknameOrEmailDuplicationTest() {
        //given
        Member saveMember = memberRepository.save(createMember());

        //when
        boolean result = memberRepository.existsByEmailOrNickname("no email", "no nickname");

        //then
        assertThat(result, is(false));
    }


    private Member createMember() {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

}

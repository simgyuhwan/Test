package com.kr.board.domain.member.repository;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.infra.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.kr.board.domain.factory.member.MemberFactory.createMember;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class MemberRepositoryTest {

    private String email = "test@email.com";
    private String nickname = "test";
    private String password = "password";
    private Member saveMember;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        saveMember = memberRepository.save(createMember());
    }

    @Test
    @DisplayName("회원 등록 성공")
    void memberRegisterTest(){
        // given
        Member newMember = Member.builder()
                .email("member@email.com")
                .password("password!!")
                .nickname("newMember")
                .build();

        //  when
        Member result = memberRepository.save(newMember);

        // then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getNickname(), is(equalTo("newMember")));
        assertThat(result.getEmail(), is(equalTo("member@email.com")));
    }

    @Test
    @DisplayName("이메일를 통한 회원 조회")
    void duplicateMemberExceptionTest(){
        // given, when
        Member result = memberRepository.findByEmail(saveMember.getEmail()).orElse(null);

        // then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getEmail(), is(equalTo(saveMember.getEmail())));
    }

    @Test
    @DisplayName("이메일 중복 확인")
    void checkEmailDuplicationTest(){
        //given, when
        boolean result = memberRepository.existsByEmailOrNickname(email, "no nickname");

        //then
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("닉네임 중복 확인")
    void checkNicknameDuplicationTest(){
        //given, when
        boolean result = memberRepository.existsByEmailOrNickname("no email", nickname);

        //then
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("닉네임 또는 이메일 중복 없음")
    void noNicknameOrEmailDuplicationTest() {
        //given, when
        boolean result = memberRepository.existsByEmailOrNickname("no email", "no nickname");

        //then
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("이메일과 비밀번호 확인시, 이메일 불일치 실패")
    void emailAndPassword_emailMismatchFailureTest() {
        //given, when
        boolean result = memberRepository.existsByEmailAndPassword("no email", password);

        //then
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("이메일과 비밀번호 확인시, 비밀번호 불일치 실패")
    void emailAndPassword_passwordMismatchFailureTest() {
        //given, when
        boolean result = memberRepository.existsByEmailAndPassword(email, "no Password");

        //then
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("이메일과 비밀번호 확인시, 일치 성공")
    void email_passwordVerificationSuccessfulTest() {
        //given, when
        boolean result = memberRepository.existsByEmailAndPassword(email, password);

        //then
        assertThat(result, is(true));
    }
}

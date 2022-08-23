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

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록")
    void memberRegisterTest(){

        // given, when
        Member result = memberRepository.save(createMember());

        // then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getNickname(), is(equalTo("test")));
        assertThat(result.getEmail(), is(equalTo("test@test.com")));
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



    private Member createMember() {
        return Member.builder()
                .email("test@test.com")
                .nickname("test")
                .password("password")
                .build();
    }

}

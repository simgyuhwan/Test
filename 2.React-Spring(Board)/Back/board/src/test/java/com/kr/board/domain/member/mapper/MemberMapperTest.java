package com.kr.board.domain.member.mapper;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.entity.Member;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {

    @Test
    @DisplayName("toDto 테스트")
    void mapperEntityToDtoTest() {
        // given
        Member member = Member.builder()
                .nickname("test")
                .email("test@test.com")
                .build();

        // when
        MemberRequestDTO result = MemberMapper.INSTANCE.toDto(member);

        // then
        assertThat(result.getNickname(), is(equalTo(member.getNickname())));
        assertEquals(result.getEmail(), member.getEmail());
    }

    @Test
    @DisplayName("toEntity 테스트")
    void mapperDtoToEntityTest() {
        // given
        MemberRequestDTO dto = MemberRequestDTO.builder()
                .nickname("test")
                .email("test@test.com")
                .build();

        // when
        Member member = MemberMapper.INSTANCE.toEntity(dto);

        // then
        assertThat(member.getNickname(), is(equalTo(dto.getNickname())));
        assertThat(member.getEmail(), is(equalTo(dto.getEmail())));
    }


}
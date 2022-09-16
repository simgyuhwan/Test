package com.kr.board.domain.member.mapper;

import com.kr.board.domain.common.mapper.member.MemberRequestMapper;
import com.kr.board.domain.member.dto.request.MemberRequest;
import com.kr.board.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberRequestMapperTest {

    @Test
    @DisplayName("Entity To Dto 테스트")
    void mapperEntityToDtoTest() {
        // given
        Member member = Member.builder()
                .nickname("test")
                .email("test@test.com")
                .build();

        // when
        MemberRequest dto = MemberRequestMapper.INSTANCE.toDto(member);

        // then
        assertThat(dto.getNickname(), is(equalTo(member.getNickname())));
        assertEquals(dto.getEmail(), member.getEmail());
    }

    @Test
    @DisplayName("Dto To Entity 테스트")
    void mapperDtoToEntityTest() {
        // given
        MemberRequest dto = MemberRequest.builder()
                .nickname("test")
                .email("test@test.com")
                .build();

        // when
        Member member = MemberRequestMapper.INSTANCE.toEntity(dto);

        // then
        assertThat(member.getNickname(), is(equalTo(dto.getNickname())));
        assertThat(member.getEmail(), is(equalTo(dto.getEmail())));
    }

    @Test
    @DisplayName("Entities To Dtos 테스트")
    void mapperEntitiesToDtosTest() {
        //given
        List<Member> members = new ArrayList<>();
        for(int i = 0; i<3; i++){
            members.add(Member.builder()
                    .nickname(i + "nickname")
                    .email(i + "email@test.com")
                    .password("password")
                    .build());
        }

        //when
        List<MemberRequest> memberRequestDTOS = MemberRequestMapper.INSTANCE.toDto(members);

        //then
        assertThat(memberRequestDTOS.size(), is(equalTo(members.size())));
        assertThat(memberRequestDTOS.get(0).getNickname(), is(equalTo(members.get(0).getNickname())));
        assertThat(memberRequestDTOS.get(0).getEmail(), is(equalTo(members.get(0).getEmail())));
    }

    @Test
    @DisplayName("Dtos To Entities 테스트")
    void mapperDtosToEntitiesTest() {
        //given
        List<MemberRequest> dtos = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            dtos.add(MemberRequest.builder()
                    .nickname(i + "nickname")
                    .email(i + "email@Test.com")
                    .password("password")
                    .build());
        }

        //when
        List<Member> members = MemberRequestMapper.INSTANCE.toEntity(dtos);

        //then
        assertThat(members.size(), is(equalTo(dtos.size())));
        assertThat(members.get(0).getNickname(), is(equalTo(dtos.get(0).getNickname())));
        assertThat(members.get(0).getEmail(), is(equalTo(dtos.get(0).getEmail())));
    }

}
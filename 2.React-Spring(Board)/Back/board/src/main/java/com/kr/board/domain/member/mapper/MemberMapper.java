package com.kr.board.domain.member.mapper;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberRequestDTO, Member> {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);
}

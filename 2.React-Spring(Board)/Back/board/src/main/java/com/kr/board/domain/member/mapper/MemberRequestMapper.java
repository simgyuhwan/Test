package com.kr.board.domain.member.mapper;

import com.kr.board.domain.common.mapper.GenericMapper;
import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberRequestMapper extends GenericMapper<MemberRequest, Member> {
    MemberRequestMapper INSTANCE = Mappers.getMapper(MemberRequestMapper.class);
}

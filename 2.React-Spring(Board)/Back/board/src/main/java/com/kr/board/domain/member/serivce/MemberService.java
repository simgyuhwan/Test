package com.kr.board.domain.member.serivce;

import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.domain.member.mapper.MemberRequestMapper;
import com.kr.board.infra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private MemberRequestMapper mapper = Mappers.getMapper(MemberRequestMapper.class);

    public Member addMember(MemberRequest dto) {
        DuplicateVerificationOfEmailOrNickname(dto);
        return memberRepository.save(mapper.toEntity(dto));
    }

    private void DuplicateVerificationOfEmailOrNickname(MemberRequest dto) {
        if(memberRepository.existsByEmailOrNickname(dto.getEmail(), dto.getNickname())){
            throw new MemberException(MemberErrorResult.DUPLICATED_MEMBER_REGISTER);
        }
    }
}

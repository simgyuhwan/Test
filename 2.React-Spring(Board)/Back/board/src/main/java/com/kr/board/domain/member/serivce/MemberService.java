package com.kr.board.domain.member.serivce;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.infra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void addMember(MemberRequestDTO dto) {
        DuplicateVerificationOfEmailOrNickname(dto);
    }

    private void DuplicateVerificationOfEmailOrNickname(MemberRequestDTO dto) {
        if(memberRepository.existsByEmailOrNickname(dto.getEmail(), dto.getNickname())){
            throw new MemberException(MemberErrorResult.DUPLICATED_MEMBER_REGISTER);
        }
    }
}

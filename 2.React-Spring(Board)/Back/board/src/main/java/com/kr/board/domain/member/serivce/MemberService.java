package com.kr.board.domain.member.serivce;

import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.dto.PasswordRequest;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.domain.common.mapper.member.MemberRequestMapper;
import com.kr.board.infra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.kr.board.domain.member.error.MemberErrorResult.*;
import static com.kr.board.domain.member.error.MemberErrorResult.DUPLICATED_MEMBER_REGISTER;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private MemberRequestMapper mapper = Mappers.getMapper(MemberRequestMapper.class);

    public void addMember(MemberRequest dto) {
        DuplicateVerificationOfEmailOrNickname(dto);
        memberRepository.save(mapper.toEntity(dto));
    }

    public void changePassword(String email, PasswordRequest passwordRequest) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        passwordVerification(member, passwordRequest.getOldPassword());
        member.changePassword(passwordRequest.getNewPassword());
    }

    private void passwordVerification(Member member, String oldPassword) {
        if(!member.comparePassword(oldPassword)){
            throw MemberException.of(PASSWORD_MISMATCH);
        }
    }

    private void DuplicateVerificationOfEmailOrNickname(MemberRequest dto) {
        if(memberRepository.existsByEmailOrNickname(dto.getEmail(), dto.getNickname())){
            throw MemberException.of(DUPLICATED_MEMBER_REGISTER);
        }
    }
}

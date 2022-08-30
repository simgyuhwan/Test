package com.kr.board.domain.member.serivce;

import com.kr.board.domain.member.dto.LoginRequest;
import com.kr.board.domain.member.error.LoginErrorResult;
import com.kr.board.domain.member.error.LoginException;
import com.kr.board.infra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kr.board.domain.member.error.LoginErrorResult.NO_MEMBER_INFORMATION_EXCEPTION;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public boolean login(LoginRequest loginRequest) {
        checkLoginRequest(loginRequest);
        return true;
    }

    private void checkLoginRequest(LoginRequest loginRequest) {
        if(!memberRepository.existsByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())){
            throw LoginException.of(NO_MEMBER_INFORMATION_EXCEPTION);
        }
    }
}

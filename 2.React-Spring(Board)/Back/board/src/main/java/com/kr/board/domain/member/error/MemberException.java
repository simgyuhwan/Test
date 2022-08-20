package com.kr.board.domain.member.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    private final MemberErrorResult memberErrorResult;
}

package com.kr.board.domain.member.error;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    private MemberErrorResult memberErrorResult;

    private MemberException(MemberErrorResult memberErrorResult) {
        this.memberErrorResult = memberErrorResult;
    }

    public static MemberException of(final MemberErrorResult memberErrorResult){
        return new MemberException(memberErrorResult);
    }
}

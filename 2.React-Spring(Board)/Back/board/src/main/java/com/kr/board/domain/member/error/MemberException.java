package com.kr.board.domain.member.error;

import lombok.*;

@Getter
public class MemberException extends RuntimeException{
    private MemberErrorResult memberErrorResult;

    private MemberException(MemberErrorResult memberErrorResult) {
        this.memberErrorResult = memberErrorResult;
    }

    private MemberException(){}

    public static MemberException of(final MemberErrorResult memberErrorResult){
        return new MemberException(memberErrorResult);
    }
}

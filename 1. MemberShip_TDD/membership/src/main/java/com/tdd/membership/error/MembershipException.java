package com.tdd.membership.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException{
    private final MembershipErrorResult errorResult;
}

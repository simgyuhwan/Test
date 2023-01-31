package com.kr.board.domain.member.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.dto.request.MemberRequest;
import com.kr.board.domain.member.serivce.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response singUp(@RequestBody @Validated MemberRequest memberRequest) {
        memberService.addMember(memberRequest);
        return Response.success();
    }

}

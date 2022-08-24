package com.kr.board.domain.member.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.serivce.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response singUp(@RequestBody MemberRequest memberRequest) {
        memberService.addMember(memberRequest);
        return Response.success();
    }

}

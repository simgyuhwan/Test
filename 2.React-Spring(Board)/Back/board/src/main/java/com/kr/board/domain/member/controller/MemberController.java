package com.kr.board.domain.member.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.member.dto.MemberRequest;
import com.kr.board.domain.member.error.MemberErrorResult;
import com.kr.board.domain.member.error.MemberException;
import com.kr.board.domain.member.serivce.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
//       validateMemberRequest(result);
        memberService.addMember(memberRequest);
        return Response.success();
    }

    private void validateMemberRequest(BindingResult result) {
        if(result.hasErrors()){
//            log.info("Signup field error : {}",
//                    result.getFieldError().getField());
//            throw new MethodArgumentNotValidException(result);
        }
    }

}

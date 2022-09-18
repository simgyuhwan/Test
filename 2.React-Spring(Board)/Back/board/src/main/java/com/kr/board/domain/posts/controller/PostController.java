package com.kr.board.domain.posts.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response register(@Validated PostRegister postRegister){
        log.info("this : {}", postRegister);

        return Response.success();
    }

}

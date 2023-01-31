package com.kr.board.domain.posts.controller;

import com.kr.board.domain.common.response.Response;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response register(@RequestBody @Validated PostRegister postRegister){
        postService.register(postRegister);
        return Response.success();
    }

    @DeleteMapping("/{postId}")
    public Response delete(@PathVariable Long postId){
        postService.deletePost(1L, postId);
        return Response.success();
    }

}

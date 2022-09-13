package com.kr.board.domain.posts.controller;

import com.kr.board.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
}

package com.kr.board.domain.posts.controller;

import com.google.gson.Gson;
import com.kr.board.domain.posts.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    private final String url = "/api/v1/posts";
    private Gson gson;
    private MockMvc mockMvc;

    @Mock
    PostService postService;

    @InjectMocks
    PostController target;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

}

package com.kr.board.domain.posts.controller;

import com.google.gson.Gson;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    private final String title = "title";
    private final String content = "content";
    private final String writer = "writer";

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

    @Test
    @DisplayName("제목 공백 예외 발생")
    void titleSpaceExceptionTest() throws Exception {
        PostRegister postRegister = PostRegister.builder()
                .content(content)
                .writer(writer)
                .build();

        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(gson.toJson(postRegister)))
                .andExpect(MockMvcResultMatchers.jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_POST.getMessage()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("내용 공백 예외 발생")
    void contentSpaceExceptionTest(){

    }

}

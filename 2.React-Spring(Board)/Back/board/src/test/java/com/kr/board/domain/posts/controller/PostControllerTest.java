package com.kr.board.domain.posts.controller;

import com.google.gson.Gson;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.error.PostErrorResult;
import com.kr.board.domain.posts.service.PostService;
import com.kr.board.infra.config.advice.PostExceptionAdvice;
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

import static com.kr.board.domain.posts.error.PostErrorResult.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(PostExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("제목 널값 예외 발생")
    void titleNullValueExceptionTest() throws Exception {
        PostRegister dto = PostRegister.builder()
                .content(content)
                .writer(writer)
                .build();

        mockMvc.perform(post(url)
                .content(gson.toJson(dto))
                .contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_POST.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("제목 공백 예외 발생")
    void titleSpaceExceptionTest() throws Exception {
        PostRegister dto = PostRegister.builder()
                .content(content)
                .writer(writer)
                .title("")
                .build();

        mockMvc.perform(post(url)
                        .content(gson.toJson(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_POST.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("내용 널값 예외 발생")
    void contentNullValueExceptionTest() throws Exception {
        PostRegister dto = PostRegister.builder()
                .title(title)
                .writer(writer)
                .build();

        mockMvc.perform(post(url)
                .content(gson.toJson(dto))
                .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_POST.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("내용 공백 예외 발생")
    void contentSpaceExceptionTest() throws Exception {
        PostRegister dto = PostRegister.builder()
                .title(title)
                .writer(writer)
                .content("")
                .build();

        mockMvc.perform(post(url)
                        .content(gson.toJson(dto))
                        .contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$..['message']")
                        .value(INCORRECT_REGISTRATION_POST.getMessage()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("게시물 등록 성공")
    void postRegistrationSuccessTest() throws Exception{
        mockMvc.perform(post(url)
                .content(gson.toJson(createDto()))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PostRegister createDto(){
        return PostRegister.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}

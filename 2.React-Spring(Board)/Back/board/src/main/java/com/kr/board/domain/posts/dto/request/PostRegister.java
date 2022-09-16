package com.kr.board.domain.posts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegister {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String writer;
}

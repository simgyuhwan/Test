package com.kr.board.domain.posts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegister {
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String writer;
}

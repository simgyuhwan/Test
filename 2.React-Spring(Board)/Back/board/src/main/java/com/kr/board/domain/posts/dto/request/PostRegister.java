package com.kr.board.domain.posts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegister {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long viewCount;
}

package com.kr.board.domain.factory.post;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.posts.entity.Post;

public class PostFactory {
    private static String writer = "user";
    private static String title = "title";
    private static String content = "content";

    public static Post createPost(Member member){
        return Post.builder()
                .id(1L)
                .title(title)
                .content(content)
                .writer(writer)
                .member(member)
                .build();
    }

}

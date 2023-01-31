package com.kr.board.domain.posts.error;

import lombok.Getter;

@Getter
public class PostException extends RuntimeException{
    private PostErrorResult postErrorResult;

    private PostException(PostErrorResult postErrorResult){ this.postErrorResult = postErrorResult;}

    private PostException(){}

    public static PostException of(final PostErrorResult postErrorResult){
        return new PostException(postErrorResult);
    }
}

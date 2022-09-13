package com.kr.board.domain.posts.service;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.posts.dto.PostDto;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.posts.repository.PostRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kr.board.domain.member.factory.MemberFactory.createMember;
import static com.kr.board.domain.posts.factory.PostFactory.createPost;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private Member saveMember;
    private Post savePost;

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService target;


    @BeforeEach
    void init(){
        saveMember = createMember();
        savePost = createPost(saveMember);
    }

    @Test
    @DisplayName("게시물 등록 성공")
    void postRegistrationSuccessTest() {
        //given
        given(postRepository.save(any(Post.class)))
                .willReturn(savePost);

        //when
        PostDto result = target.register(createPostDto(savePost));

        //then
        assertThat(result, is(notNullValue()));
        assertThat(result.getTitle(),
                is(equalTo(savePost.getTitle())));
    }

    private PostDto createPostDto(Post post){
        return PostDto.builder()
                .writer(post.getWriter())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}

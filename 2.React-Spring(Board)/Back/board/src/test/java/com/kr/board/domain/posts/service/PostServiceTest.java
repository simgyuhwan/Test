package com.kr.board.domain.posts.service;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.posts.error.PostException;
import com.kr.board.domain.posts.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.CORBA.NO_PERMISSION;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.kr.board.domain.factory.member.MemberFactory.createFullMember;
import static com.kr.board.domain.factory.member.MemberFactory.createMember;
import static com.kr.board.domain.factory.post.PostFactory.createPost;
import static com.kr.board.domain.posts.error.PostErrorResult.NO_PERMISSION_TO_DELETE_POSTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    private Member saveMember;
    private Post savePost;
    private final Long noSaveMemberId = 9999L;

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService target;


    @BeforeEach
    void init(){
        saveMember = createFullMember();
        savePost = createPost(saveMember);
    }

    @Test
    @DisplayName("게시물 등록 성공")
    void postRegistrationSuccessTest() {
        //given
        given(postRepository.save(any(Post.class)))
                .willReturn(savePost);

        //when
        PostRegister result = target.register(createPostDto(savePost));

        //then
        assertThat(result, is(notNullValue()));
        assertThat(result.getTitle(),
                is(equalTo(savePost.getTitle())));
    }

    @Test
    @DisplayName("게시물 삭제시, 삭제 권한 없음 예외")
    void noPermissionToDeletePostsExceptionTest(){
        //given
        given(postRepository.findById(any()))
                .willReturn(Optional.of(savePost));

        //when
        PostException result = assertThrows(PostException.class,
                () -> target.deletePost(noSaveMemberId, savePost.getId()));

        //then
        assertThat(result.getPostErrorResult(),
                is(equalTo(NO_PERMISSION_TO_DELETE_POSTS)));
    }

    @Test
    @DisplayName("게시물 삭제시, 존재하지 않은 게시물 예외")
    void postsThatDoNotExistExceptionTest(){
        //given
        given(postRepository.findById(any()))
                .willThrow(EntityNotFoundException.class);

        //when, then
        assertThrows(EntityNotFoundException.class,
                () -> target.deletePost(saveMember.getId(), savePost.getId()));
    }

    @Test
    @DisplayName("게시물 삭제 성공")
    void postDeletionSuccess(){
        //given
        given(postRepository.findById(any()))
                .willReturn(Optional.of(savePost));

        //when
        target.deletePost(saveMember.getId(), savePost.getId());

        //then
        verify(postRepository).delete(savePost);
    }

    private PostRegister createPostDto(Post post){
        return PostRegister.builder()
                .writer(post.getWriter())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}

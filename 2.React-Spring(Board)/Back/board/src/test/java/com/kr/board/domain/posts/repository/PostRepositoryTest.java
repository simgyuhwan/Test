package com.kr.board.domain.posts.repository;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.posts.factory.PostFactory;
import com.kr.board.infra.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.kr.board.domain.member.factory.MemberFactory.createMember;
import static com.kr.board.domain.posts.factory.PostFactory.createPost;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class PostRepositoryTest {

    private Post savePost;
    private Member saveMember;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void init() {
        saveMember = memberRepository.save(createMember());
        clear();
        savePost = postRepository.save(createPost(saveMember));
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void postRegistrationSuccessfulTest() {
        //given
        Post post = Post.builder()
                .title("title")
                .content("content")
                .writer("userNickname")
                .member(saveMember)
                .build();
        //when
        Post result = postRepository.save(post);

        //then
        assertThat(result.getId(), notNullValue());
        assertThat(result.getTitle(), is(equalTo(post.getTitle())));
        assertThat(result.getContent(), is(equalTo(post.getContent())));
    }

    @Test
    @DisplayName("작성자를 통한 게시글 조회")
    void findByuWriterTest() {
        //given, when
        String writer = savePost.getWriter();
        List<Post> results = postRepository.findByWriter(writer);

        //then
        assertThat(results.get(0).getWriter(), is(equalTo(writer)));
        assertThat(results.size(), is(equalTo(1)));
    }

    @Test
    @DisplayName("MemberId를 통한 게시글 조회")
    void findByMemberIdTest(){
        //when
        List<Post> results = postRepository.findAllByMemberId(saveMember.getId());

        //then
        assertThat(results.size(), is(equalTo(1)));
    }

    @Test
    @DisplayName("MemberId를 통한 게시글 조회 없음")
    void findByMemberIdFailureTest() {
        //given, when
        Long noMemberId = 100L;
        List<Post> posts = postRepository.findAllByMemberId(noMemberId);

        //then
        assertThat(posts.size(), is(equalTo(0)));
    }

    @Test
    @DisplayName("제목명 포함된 게시글 모두 조회")
    void findByTitleContainingTest(){
        //given, when
        List<Post> posts = postRepository
                .findByTitleContainingOrContentContaining(savePost.getTitle(),
                        "no Content");

        //then
        assertThat(posts.size(),
                is(equalTo(1)));
    }

    @Test
    @DisplayName("내용이 포함된 게시글 모두 조회")
    void findByContentTest() {
        //given, when
        List<Post> posts = postRepository.findByTitleContainingOrContentContaining("no title",
                        savePost.getContent());
        //then
        assertThat(posts.size(),
                is(equalTo(1)));
    }

    @Test
    @DisplayName("제목, 컨텐츠 포함된 조회 실패")
    void findByTitleContainingOrContentContainingFailureTest() {
        //given, when
        List<Post> findPost = postRepository
                .findByTitleContainingOrContentContaining("no title", "no content");
        //then
        assertThat(findPost.size(), is(equalTo(0)));
    }

    private void clear() {
        em.flush();
        em.clear();
    }
}

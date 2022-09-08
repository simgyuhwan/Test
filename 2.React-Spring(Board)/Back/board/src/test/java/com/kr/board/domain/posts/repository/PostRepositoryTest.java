package com.kr.board.domain.posts.repository;

import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.member.factory.MemberFactory;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.infra.repository.MemberRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.kr.board.domain.member.factory.MemberFactory.createMember;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class PostRepositoryTest {

    private String writer = "firstWriter";
    private String title = "title1";
    private String content = "content1";
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
        savePost = postRepository.save(createPost());
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void postRegistrationSuccessfulTest() {
        //given
        Post post = Post.builder()
                .title("title")
                .content("content")
                .writer("userNickname")
                .member(createMember())
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
        List<Post> results = postRepository.findByWriter(writer);

        //then
        assertThat(results.get(0).getWriter(), is(equalTo(writer)));
        assertThat(results.size(), is(equalTo(1)));
    }

    @Test
    @DisplayName("MemberId를 통한 게시글 조회")
    void findByUserIdTest(){
        //given
        List<Post> results = postRepository.findAllByMemberId(saveMember
                                                                    .getId());
        //when
        Post savePost = results.get(0);

        //then
        assertThat(savePost.getWriter(), is(equalTo(writer)));
        assertThat(results.size(), is(equalTo(1)));
        assertThat(savePost.getMember().getId(),
                                            is(equalTo(saveMember.getId())));
    }

    @Test
    @DisplayName("제목명 포함된 게시글 모두 조회")
    void findByTitleContainingTest(){
        //given, when
        Post findPost = postRepository
                .findByTitleContainingOrContentContaining(title, "no Content")
                .get(0);

        //then
        assertThat(findPost.getTitle(), is(equalTo(title)));
    }

    @Test
    @DisplayName("내용이 포함된 게시글 모두 조회")
    void findByContentTest() {
        //given, when
        Post findPost = postRepository.findByTitleContainingOrContentContaining("no title", content)
                .get(0);
        //then
        assertThat(findPost.getContent(), is(equalTo(content)));
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
    private Post createPost(){
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .member(saveMember)
                .build();
    }

    private void clear() {
        em.flush();
        em.clear();
    }
}

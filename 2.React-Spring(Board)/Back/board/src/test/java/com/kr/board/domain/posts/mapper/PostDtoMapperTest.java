package com.kr.board.domain.posts.mapper;

import com.kr.board.domain.common.mapper.post.PostDtoMapper;
import com.kr.board.domain.member.entity.Member;
import com.kr.board.domain.factory.member.MemberFactory;
import com.kr.board.domain.posts.dto.PostDto;
import com.kr.board.domain.posts.entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kr.board.domain.factory.post.PostFactory.createPost;
import static org.assertj.core.api.Assertions.assertThat;

public class PostDtoMapperTest {

    private Member member;

    @BeforeEach
    void init(){
        member = MemberFactory.createMember();
    }

    @Test
    @DisplayName("Entity To Dto")
    void mapperEntityToDtoTest(){
        //given
        Post post = createPost(member);

        //when
        PostDto dto = PostDtoMapper.INSTANCE.toDto(post);

        //then
        assertThat(post.getContent()).isEqualTo(dto.getContent());
        assertThat(post.getTitle()).isEqualTo(dto.getTitle());
        assertThat(post.getId()).isEqualTo(dto.getId());
    }

    @Test
    @DisplayName("Dto To Entity")
    void mapperDtoToEntityTest(){
        //given
        PostDto dto = PostDto.builder()
                .writer("writer")
                .title("title")
                .id(1L)
                .build();

        //when
        Post post = PostDtoMapper.INSTANCE.toEntity(dto);

        //then
        assertThat(post.getWriter()).isEqualTo("writer");
        assertThat(post.getTitle()).isEqualTo("title");
        assertThat(post.getId()).isEqualTo(1L);
    }
}

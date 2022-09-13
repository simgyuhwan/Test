package com.kr.board.domain.posts.service;

import com.kr.board.domain.posts.dto.PostDto;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.common.mapper.post.PostDtoMapper;
import com.kr.board.domain.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private PostDtoMapper mapper = Mappers.getMapper(PostDtoMapper.class);

    public PostDto register(PostDto postDto){
        Post post = postRepository.save(mapper
                                    .toEntity(postDto));
        return mapper.toDto(post);
    }
}

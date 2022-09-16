package com.kr.board.domain.posts.service;

import com.kr.board.domain.posts.dto.PostRegister;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.common.mapper.post.PostRegisterMapper;
import com.kr.board.domain.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private PostRegisterMapper mapper = Mappers.getMapper(PostRegisterMapper.class);

    public PostRegister register(PostRegister postDto){
        Post post = postRepository.save(mapper
                                    .toEntity(postDto));
        return mapper.toDto(post);
    }
}

package com.kr.board.domain.posts.service;

import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.entity.Post;
import com.kr.board.domain.common.mapper.post.PostRegisterMapper;
import com.kr.board.domain.posts.error.PostErrorResult;
import com.kr.board.domain.posts.error.PostException;
import com.kr.board.domain.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.kr.board.domain.posts.error.PostErrorResult.NO_PERMISSION_TO_DELETE_POSTS;

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

    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        checkPostOwner(userId, post);
        postRepository.delete(post);
    }

    private void checkPostOwner(Long userId, Post post) {
        if(!post.ownerVerification(userId)) {
            throw PostException.of(NO_PERMISSION_TO_DELETE_POSTS);
        }
    }
}

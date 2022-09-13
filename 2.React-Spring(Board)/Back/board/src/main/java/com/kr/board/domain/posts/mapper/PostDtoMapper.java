package com.kr.board.domain.posts.mapper;

import com.kr.board.domain.common.mapper.GenericMapper;
import com.kr.board.domain.posts.dto.PostDto;
import com.kr.board.domain.posts.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDtoMapper extends GenericMapper<PostDto, Post> {
    PostDtoMapper INSTANCE = Mappers.getMapper(PostDtoMapper.class);
}

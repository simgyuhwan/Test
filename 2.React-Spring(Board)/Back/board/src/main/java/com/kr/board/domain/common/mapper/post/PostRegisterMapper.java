package com.kr.board.domain.common.mapper.post;

import com.kr.board.domain.common.mapper.GenericMapper;
import com.kr.board.domain.posts.dto.request.PostRegister;
import com.kr.board.domain.posts.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostRegisterMapper extends GenericMapper<PostRegister, Post> {
    PostRegisterMapper INSTANCE = Mappers.getMapper(PostRegisterMapper.class);
}

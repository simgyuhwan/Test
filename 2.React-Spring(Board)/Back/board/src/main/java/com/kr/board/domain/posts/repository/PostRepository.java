package com.kr.board.domain.posts.repository;

import com.kr.board.domain.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByWriter(String writer);

    @Query("SELECT p FROM Post p WHERE p.member.id = :memberId")
    List<Post> findAllByMemberId(@Param("memberId") Long memberId);

    List<Post> findByTitleContainingOrContentContaining(String title, String content);
}

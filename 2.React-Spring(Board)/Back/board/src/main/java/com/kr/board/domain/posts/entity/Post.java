package com.kr.board.domain.posts.entity;

import com.kr.board.domain.common.BaseEntity;
import com.kr.board.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "view_count")
    @Builder.Default
    private Long viewCount = 1L;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
//
//    @Builder
//    public Post(Long id, String title, String content, String writer, Member member) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.member = member;
//    }
}

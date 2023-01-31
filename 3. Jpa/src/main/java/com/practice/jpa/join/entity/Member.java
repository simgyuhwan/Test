package com.practice.jpa.join.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Member.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
@Entity
@Getter
@ToString(exclude = { "team" })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }
}

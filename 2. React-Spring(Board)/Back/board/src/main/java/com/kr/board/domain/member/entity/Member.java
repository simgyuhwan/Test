package com.kr.board.domain.member.entity;

import com.kr.board.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Builder
public class Member extends BaseEntity {

    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    public boolean comparePassword(String oldPassword){
        return this.password.equals(oldPassword);
    }

    public void changePassword(String oldPassword) {
        this.password = oldPassword;
    }

    public boolean equalsToId(Long userId){
        return id == userId;
    }
}

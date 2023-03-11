package com.practice.jpa.Inheritence.joined;

import com.practice.jpa.Inheritence.joined.sub.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * User.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "user_id", unique = false, nullable = false)
    private String userId;


    @Column(nullable = false, name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;
}

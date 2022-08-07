package com.tdd.membership.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MemberShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberShipType memberShipName;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer point;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime updatedAt;
}

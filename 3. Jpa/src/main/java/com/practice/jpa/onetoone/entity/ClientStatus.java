package com.practice.jpa.onetoone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ClientStatus.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.02.01
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_status_id")
    private Long id;

    private String active;

    @OneToOne(orphanRemoval = true, mappedBy = "clientStatus")
    private Client client;
}

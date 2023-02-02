package com.practice.jpa.onetoone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Client.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.02.01
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    private String clientName;
    private String clientIp;

    @OneToOne
    @JoinColumn(name = "client_status_id")
    private ClientStatus clientStatus;

}

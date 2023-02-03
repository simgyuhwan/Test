package com.practice.jpa.todto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * File.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.02.03
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String saveName;

}

package com.practice.jpa.Inheritence.joined;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Seller.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@DiscriminatorValue("seller")
public class Seller extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer money;

    @ToString.Exclude
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Item> sellerItems;

    private Seller(Integer money, List<Item> items, String name, String userId) {
        this.money = money;
        this.sellerItems = items;
        setName(name);
        setUserId(userId);
    }

    public static Seller create(Integer money,List<Item> items, String name, String userId) {
        return new Seller( money,items,name,userId);
    }
}

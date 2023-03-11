package com.practice.jpa.Inheritence.joined.sub;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Item.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "seller_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    private Integer price;

    private Item(String name, Integer price){
        this.itemName = name;
        this.price = price;
    }

    public static Item create(String name, Integer price) {
        return new Item(name, price);
    }
}

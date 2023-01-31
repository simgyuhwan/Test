package com.practice.jpa.repository.joined;

import com.practice.jpa.Inheritence.joined.Item;
import com.practice.jpa.Inheritence.joined.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SellerRepositoryTest.java
 * Class 설명을 작성하세요.
 *
 * @author sgh
 * @since 2023.01.31
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

    @BeforeEach
    void beforeEach() {
        Item item = Item.create("장난감", 10000);
        List<Item> items = new ArrayList<>();
        items.add(item);
        Seller seller = Seller.create(10000, items, "판매자", "sellerId");
        sellerRepository.save(seller);
    }

    @Test
    void findSellerTest(){
        Seller seller = sellerRepository.findByName("판매자").orElse(null);
        assertThat(seller.getName()).isEqualTo("판매자");
    }
}
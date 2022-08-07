package com.tdd.membership.repository;

import com.tdd.membership.entity.MemberShip;
import com.tdd.membership.entity.MemberShipType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberShipRepositoryTest {

    @Autowired
    private MemberShopRepository memberShopRepository;

    @Test
    public void 맴버십등록(){
        // given
        final MemberShip memberShip = MemberShip.builder()
                .userId("userId")
                .memberShipType(MemberShipType.NAVER)
                .point(10000)
                .build();

        // when
        final MemberShip result = memberShopRepository.save(memberShip);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getPoint()).isEqualTo(10000);
        assertThat(result.getMemberShipType()).isEqualTo(MemberShipType.NAVER);
    }

    @Test
    public void 맴버십이존재하는지테스트(){
        // given
        final MemberShip memberShip = MemberShip.builder()
                .userId("userId")
                .memberShipType(MemberShipType.NAVER)
                .point(10000)
                .build();
        // when
        memberShopRepository.save(memberShip);
        final MemberShip findMemberShip = memberShopRepository.findByUserIdAndMemberShipType("userId", MemberShipType.NAVER);

        // then
        assertThat(findMemberShip).isNotNull();
        assertThat(findMemberShip.getId()).isNotNull();
        assertThat(findMemberShip.getUserId()).isEqualTo("userId");
        assertThat(findMemberShip.getMemberShipType()).isEqualTo(MemberShipType.NAVER);
        assertThat(findMemberShip.getPoint()).isEqualTo(10000);
    }
}

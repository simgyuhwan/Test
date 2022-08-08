package com.tdd.membership.repository;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberShipRepositoryTest {

    @Autowired
    private MemberShipRepository memberShopRepository;

    @Test
    public void 맴버십등록(){
        // given
        final Membership memberShip = Membership.builder()
                .userId("userId")
                .memberShipType(MembershipType.NAVER)
                .point(10000)
                .build();

        // when
        final Membership result = memberShopRepository.save(memberShip);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getPoint()).isEqualTo(10000);
        assertThat(result.getMemberShipType()).isEqualTo(MembershipType.NAVER);
    }

    @Test
    public void 맴버십이존재하는지테스트(){
        // given
        final Membership memberShip = Membership.builder()
                .userId("userId")
                .memberShipType(MembershipType.NAVER)
                .point(10000)
                .build();
        // when
        memberShopRepository.save(memberShip);
        final Membership findMemberShip = memberShopRepository.findByUserIdAndMemberShipType("userId", MembershipType.NAVER);

        // then
        assertThat(findMemberShip).isNotNull();
        assertThat(findMemberShip.getId()).isNotNull();
        assertThat(findMemberShip.getUserId()).isEqualTo("userId");
        assertThat(findMemberShip.getMemberShipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findMemberShip.getPoint()).isEqualTo(10000);
    }
}

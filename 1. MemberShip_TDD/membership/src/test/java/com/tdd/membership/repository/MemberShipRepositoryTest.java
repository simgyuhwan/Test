package com.tdd.membership.repository;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import org.hibernate.boot.TempTableDdlTransactionHandling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberShipRepositoryTest {

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Test
    public void 맴버십등록(){
        // given
        final Membership memberShip = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        // when
        final Membership result = memberShipRepository.save(memberShip);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getPoint()).isEqualTo(10000);
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
    }

    @Test
    public void 맴버십이존재하는지테스트(){
        // given
        final Membership memberShip = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();
        // when
        memberShipRepository.save(memberShip);
        final Membership findMemberShip = memberShipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        // then
        assertThat(findMemberShip).isNotNull();
        assertThat(findMemberShip.getId()).isNotNull();
        assertThat(findMemberShip.getUserId()).isEqualTo("userId");
        assertThat(findMemberShip.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findMemberShip.getPoint()).isEqualTo(10000);
    }

    @Test
    public void 맴버십조회_사이즈가0() {
        //given

        //when
        List<Membership> result = memberShipRepository.findAllByUserId("userId");

        //then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void 맴버십조회_사이즈가2() {
        //given
        final Membership naverMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        Membership kakaoMembership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.KAKAO)
                .point(10000)
                .build();

        memberShipRepository.save(naverMembership);
        memberShipRepository.save(kakaoMembership);

        //when
        List<Membership> result = memberShipRepository.findAllByUserId("userId");

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void 맴버십추가후삭제() {
        //given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        final Membership saveMembership = memberShipRepository.save(membership);

        //when
        memberShipRepository.deleteById(saveMembership.getId());
        //then

    }


}

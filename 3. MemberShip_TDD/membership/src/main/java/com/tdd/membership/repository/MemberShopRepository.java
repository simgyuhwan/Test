package com.tdd.membership.repository;

import com.tdd.membership.entity.MemberShip;
import com.tdd.membership.entity.MemberShipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShopRepository extends JpaRepository<MemberShip, Long> {

    MemberShip findByUserIdAndMemberShipType(final String userId, final MemberShipType memberShipType);
}

package com.tdd.membership.repository;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMemberShipType(final String userId, final MembershipType memberShipType);
}

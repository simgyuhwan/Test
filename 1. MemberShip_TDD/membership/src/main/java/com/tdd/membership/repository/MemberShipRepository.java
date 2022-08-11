package com.tdd.membership.repository;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberShipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMembershipType(final String userId, final MembershipType memberShipType);

    List<Membership> findAllByUserId(final String userId);
}

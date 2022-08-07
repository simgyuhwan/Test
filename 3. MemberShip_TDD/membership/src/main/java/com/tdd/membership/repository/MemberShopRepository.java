package com.tdd.membership.repository;

import com.tdd.membership.entity.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShopRepository extends JpaRepository<MemberShip, Long> {
}

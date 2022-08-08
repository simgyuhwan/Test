package com.tdd.membership.service;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import com.tdd.membership.repository.MemberShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MemberShipRepository memberShipRepository;

    public Membership addMembership(final String userId, final MembershipType memberShipType, final Integer point) {
        // 유효성 검증(조회)
        Membership result = memberShipRepository.findByUserIdAndMemberShipType(userId, memberShipType);
        if(result != null){
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .memberShipType(memberShipType)
                .point(point)
                .build();

        return memberShipRepository.save(membership);
    }
}

package com.tdd.membership.service;

import com.tdd.membership.dto.MembershipDetailResponse;
import com.tdd.membership.dto.MembershipResponse;
import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import com.tdd.membership.repository.MemberShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MemberShipRepository memberShipRepository;
    private final PointService ratePointService;

    public MembershipResponse addMembership(final String userId, final MembershipType memberShipType, final Integer point) {
        // 유효성 검증(조회)
        Membership result = memberShipRepository.findByUserIdAndMembershipType(userId, memberShipType);
        if(result != null){
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        validatePoint(point);

        final Membership membership = Membership.builder()
                .userId(userId)
                .membershipType(memberShipType)
                .point(point)
                .build();

        final Membership saveMembership = memberShipRepository.save(membership);

        return MembershipResponse.builder()
                .membershipType(saveMembership.getMembershipType())
                .id(saveMembership.getId())
                .build();
    }

    public List<MembershipDetailResponse> getMembershipList(String userId) {
        final List<Membership> membershipList = memberShipRepository.findAllByUserId(userId);

        return membershipList.stream()
                .map(v -> MembershipDetailResponse.builder()
                        .id(v.getId())
                        .membershipType(v.getMembershipType())
                        .point(v.getPoint())
                        .createdAt(v.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public void removeMembership(final Long membershipId, final String userId) {
        Optional<Membership> optionalMembership = memberShipRepository.findById(membershipId);
        Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        if( !membership.getUserId().equals(userId) ) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }
        memberShipRepository.deleteById(membershipId);
    }

    public MembershipDetailResponse getMembership(final Long membershipId, final String userId) {
        Optional<Membership> optionalMembership = memberShipRepository.findById(membershipId);
        Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        if(!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        return MembershipDetailResponse.builder()
                .id(membership.getId())
                .membershipType(membership.getMembershipType())
                .point(membership.getPoint())
                .createdAt(membership.getCreatedAt())
                .build();
    }

    @Transactional
    public void accumulateMembershipPoint(final Long membershipId, final String userId, final int amount) {
        Optional<Membership> optionalMembership = memberShipRepository.findById(membershipId);
        Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        if( !membership.getUserId().equals(userId) ) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        validatePoint(amount);
        final int additionalAmount = ratePointService.calculateAmount(amount);

        membership.setPoint(additionalAmount + membership.getPoint());
    }

    private void validatePoint(int amount) {
        if(!ratePointService.isAccumulatedPoint(amount)){
            throw new MembershipException(MembershipErrorResult.INVALID_POINT_VALUE);
        }
    }
}

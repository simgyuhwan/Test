package com.tdd.membership.dto;

import com.tdd.membership.entity.MembershipType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MembershipDetailResponse {
    private Long id;
    private MembershipType membershipType;
    private Integer point;
    private LocalDateTime createdAt;

}

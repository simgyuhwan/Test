package com.tdd.membership.dto;

import com.tdd.membership.annotation.ValidationGroups;
import com.tdd.membership.annotation.ValidationGroups.MembershipAccumulateMarker;
import com.tdd.membership.entity.MembershipType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.tdd.membership.annotation.ValidationGroups.*;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MembershipRequest {

    @NotNull(groups = {MembershipAddMarker.class, MembershipAccumulateMarker.class})
    @Min(0)
    private final Integer point;

    @NotNull(groups = {MembershipAddMarker.class})
    private final MembershipType membershipType;
}

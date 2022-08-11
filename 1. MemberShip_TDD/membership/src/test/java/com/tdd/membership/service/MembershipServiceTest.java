package com.tdd.membership.service;

import com.tdd.membership.dto.MembershipDetailResponse;
import com.tdd.membership.dto.MembershipResponse;
import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import com.tdd.membership.repository.MemberShipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    private final String userId = "userId";
    private final MembershipType memberShipType = MembershipType.NAVER;
    private final Integer point = 10000;
    private final Long membershipId = 1L;

    @InjectMocks
    MembershipService target;

    @Mock
    MemberShipRepository memberShipRepository;

    // 1. 실패 하는 테스트
    @Test
    public void 맴버십등록_이미존재함() {
        //given
        doReturn(Membership.builder().build()).when(memberShipRepository).findByUserIdAndMembershipType(userId, memberShipType);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, memberShipType, point));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

    @Test
    public void 맴버십등록성공() {
        //given
        doReturn(null).when(memberShipRepository).findByUserIdAndMembershipType(userId, memberShipType);
        doReturn(membership()).when(memberShipRepository).save(any(Membership.class));

        //when
        final MembershipResponse result = target.addMembership(userId, memberShipType, point);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

        //verify
        verify(memberShipRepository, times(1)).findByUserIdAndMembershipType(userId, memberShipType);
        verify(memberShipRepository, times(1)).save(any(Membership.class));
    }

    @Test
    public void 맴버십목록조회() {
        //given
        given(memberShipRepository.findAllByUserId(userId))
                .willReturn(Arrays.asList(
                        Membership.builder().build(),
                        Membership.builder().build(),
                        Membership.builder().build()
                ));

        //when
        final List<MembershipDetailResponse> result = target.getMembershipList(userId);

        //then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void 맴버십상세조회실패_존재하지않음() {
        //given
        given(memberShipRepository.findById(membershipId)).willReturn(Optional.empty());

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.getMembership(membershipId, userId));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    public void 맴버십상세조회실패_본인이아님() {
        //given
        given(memberShipRepository.findById(membershipId)).willReturn(Optional.empty());

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.getMembership(membershipId, "notower"));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.MEMBERSHIP_NOT_FOUND);
    }

    @Test
    public void 맴버십상세조회성공() {
        //given
        given(memberShipRepository.findById(membershipId)).willReturn(Optional.of(membership()));

        //when
        final MembershipDetailResponse result = target.getMembership(membershipId, userId);

        //then
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(point);
    }

    private Membership membership(){
        return Membership.builder()
                .id(-1L)
                .userId(userId)
                .point(point)
                .membershipType(MembershipType.NAVER)
                .build();
    }

}

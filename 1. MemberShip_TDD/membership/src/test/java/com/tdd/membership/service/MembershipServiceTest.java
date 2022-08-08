package com.tdd.membership.service;

import com.tdd.membership.entity.Membership;
import com.tdd.membership.entity.MembershipType;
import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import com.tdd.membership.repository.MemberShipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    private final String userId = "userId";
    private final MembershipType memberShipType = MembershipType.NAVER;
    private final Integer point = 10000;

    @InjectMocks
    MembershipService target;

    @Mock
    MemberShipRepository memberShipRepository;

    // 1. 실패하는 테스트
    @Test
    public void 맴버십등록_이미존재함() {
        //given
        doReturn(Membership.builder().build()).when(memberShipRepository).findByUserIdAndMemberShipType(userId, memberShipType);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, memberShipType, point));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

}

package com.tdd.membership.controller;

import com.google.gson.Gson;
import com.tdd.membership.advice.GlobalExceptionHandler;
import com.tdd.membership.dto.MembershipDetailResponse;
import com.tdd.membership.dto.MembershipRequest;
import com.tdd.membership.dto.MembershipResponse;
import com.tdd.membership.entity.MembershipType;
import com.tdd.membership.error.MembershipErrorResult;
import com.tdd.membership.error.MembershipException;
import com.tdd.membership.service.MembershipService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.tdd.membership.controller.MembershipConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController target;
    private MockMvc mockMvc;
    private Gson gson;
    private final String url = "/api/v1/memberships";

    @Mock
    private MembershipService membershipService;

    @BeforeEach
    public void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
    @ParameterizedTest
    @MethodSource("invalidMembershipAddParameter")
    public void 맴버십등록실패_잘못된파라미터(final Integer point, final MembershipType membershipType) throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(
                post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(point, membershipType)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십등록실패_사용자식별값이헤더에없음() throws Exception {
        //given

        //when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .content(gson.toJson(membershipRequest(1000, MembershipType.NAVER)))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십등록실패_포인트가음수() throws Exception {
        //given
        doThrow(new MembershipException(MembershipErrorResult.INVALID_POINT_VALUE))
                .when(membershipService).addMembership("12345", MembershipType.NAVER, -1);
        //when
        final ResultActions result = mockMvc.perform(
                post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(-1, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십등록실패_맴버십종류가null() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(
                post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, null)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십등록실패_MemberService에서에러Throw() throws Exception {
        //given
        final String url = "/api/v1/memberships";
        doThrow(new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER))
                .when(membershipService)
                .addMembership("12345", MembershipType.NAVER, 10000);

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .header(USER_ID_HEADER, "12345")
                .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십등록() throws Exception {
        //given
        final MembershipResponse membershipResponse = MembershipResponse.builder()
                .id(-1L)
                .membershipType(MembershipType.NAVER).build();
        given(membershipService.addMembership("12345", MembershipType.NAVER, 10000)).willReturn(membershipResponse);

        //when
        ResultActions result = mockMvc.perform(
                post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isCreated());

        MembershipResponse response = gson.fromJson(result.andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8), MembershipResponse.class);

        assertThat(response.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void 맴버십목록조희실패_사용자식별값이헤더에없음() throws Exception {
        //given

        //when
        final ResultActions result = mockMvc.perform(get(url));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십목록조회성공() throws Exception {
        //given
        given(membershipService.getMembershipList("12345")).willReturn(Arrays.asList(
                MembershipDetailResponse.builder().build(),
                MembershipDetailResponse.builder().build(),
                MembershipDetailResponse.builder().build()
        ));

        //when
        ResultActions result = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, "12345"));

        //then
        result.andExpect(status().isOk());
    }

    @Test
    public void 맴버십상세조회실패_사용자식별값이헤더에없음() throws Exception {
        //given

        //when
        final ResultActions result = mockMvc.perform(get(url));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십상세조회실패_맴버십이존재하지않음() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1";
        given(membershipService.getMembership(-1L, "12345"))
                .willThrow(new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, "12345"));

        //then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void 맴버십상세조회성공() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1";
        given(membershipService.getMembership(-1L, "12345"))
                .willReturn(MembershipDetailResponse.builder().build());

        //when
        ResultActions result = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, "12345")
                .param("membershipType", MembershipType.NAVER.name()));

        //then
        result.andExpect(status().isOk());
    }

    @Test
    public void 맴버십삭제실패_사용자식별값이헤더에없음() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1";

        //when
        ResultActions result = mockMvc.perform(delete(url));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십삭제성공() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1";

        //when
        ResultActions result = mockMvc.perform(delete(url)
                .header(USER_ID_HEADER, "12345"));

        //then
        result.andExpect(status().isNoContent());
    }

    @Test
    public void 맴버십적립실패_사용자식별값이헤더에없음() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1/accumulate";

        //when
        ResultActions result = mockMvc.perform(post(url)
                .content(gson.toJson(membershipRequest(1000)))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십적립실패_포인트가음수() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1/accumulate";
        doThrow(new MembershipException(MembershipErrorResult.INVALID_POINT_VALUE))
                .when(membershipService).accumulateMembershipPoint(-1L, "12345", -1);

        //when
        ResultActions result = mockMvc.perform(
                post(url)
                        .header(USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(-1, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void 맴버십정립성공() throws Exception {
        //given
        final String url = "/api/v1/memberships/-1/accumulate";

        //when
        ResultActions result = mockMvc.perform(post(url)
                .header(USER_ID_HEADER, "12345")
                .content(gson.toJson(membershipRequest(10000)))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());
    }

    private MembershipRequest membershipRequest(final Integer point) {
        return MembershipRequest.builder()
                .point(point)
                .build();
    }

    private static Stream<Arguments> invalidMembershipAddParameter() {
        return Stream.of(
                Arguments.of(null, MembershipType.NAVER),
                Arguments.of(10000, null)
        );
    }

    private MembershipRequest membershipRequest(final Integer point, final MembershipType membershipType) {
        return MembershipRequest.builder()
                .point(point)
                .membershipType(membershipType)
                .build();
    }

}

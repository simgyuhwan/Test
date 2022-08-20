package com.kr.board.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberRequestDTO {

    private Long id;
    private String email;
    private String nickname;
    private String password;
}

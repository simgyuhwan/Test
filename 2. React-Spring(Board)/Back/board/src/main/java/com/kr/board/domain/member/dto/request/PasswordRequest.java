package com.kr.board.domain.member.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}

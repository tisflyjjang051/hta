package com.jjang051.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "id는 필수입력사항입니다.")
    private String userId;

    @NotBlank(message = "password는 필수입력사항입니다.")
    private String password;
}

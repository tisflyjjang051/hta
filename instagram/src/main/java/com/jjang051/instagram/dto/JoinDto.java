package com.jjang051.instagram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {
    @NotBlank
    @Size(min = 4, max=20)
    private String userId;

    @NotBlank
    @Size(min = 4, max=20)
    private String password;

    @Email(message = "이메일 형식이 맞질 않습니다.")
    private String email;


    @NotBlank
    private String name;


}

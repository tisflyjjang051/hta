package com.jjang051.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JoinDto {
    @NotBlank
    @Size(min=3,max=20)
    private String userId;

    @NotBlank
    @Size(min=2,max=20)
    private String name;

    /*@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}",
            message = "영문자 숫자 특수문자 조합해서 8자 16자 이하로 입력해주세요.")*/

    @NotBlank
    private String password;

    @Email
    private String email;

    private String role;

    private Integer status;

    private Date regdate;

}

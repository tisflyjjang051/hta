package com.jjang051.instagram.dto;

import com.jjang051.instagram.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;

@Setter
@Getter
@ToString
public class MemberUpdateDto {

    private int id;

    @NotBlank
    private String userId;
    @NotBlank
    private String password; // 필수
    @NotBlank
    private String name; // 필수
    private String description;
    private String phone;
    private String email;
    private String mbti;



    // 조금 위함함. 코드 수정이 필요할 예정
    public Member toEntity() {
        return Member.builder()
                .id(id)
                .userId(userId)
                .name(name)
                .password(password)
                .description(description)
                .phone(phone)
                .mbti(mbti)
                .email(email)
                .build();
    }
}

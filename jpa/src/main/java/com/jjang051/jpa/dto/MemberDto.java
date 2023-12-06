package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member02;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
    private String userId;
    private String nickName;
    private String email;
    private int age;
    private String address;
    private String role;
    private int id;
    private String password;


    public static MemberDto fromEntity(Member02 member02) {
        return MemberDto.builder()
                .id(member02.getId())
                .userId(member02.getUserId())
                .password(member02.getPassword())
                .role(member02.getRole())
                .email(member02.getEmail())
                .nickName(member02.getNickName())
                .age(member02.getAge())
                .build();
    }


}

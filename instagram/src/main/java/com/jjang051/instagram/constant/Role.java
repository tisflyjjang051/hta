package com.jjang051.instagram.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER("role_user"),
    ROLE_ADMIN("role_admin");


    private final String role;
}

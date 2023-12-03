package com.jjang051.photogram02.dto;

import com.jjang051.photogram02.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomUserDetailsDto implements UserDetails {
    private User loggedUser;

    public CustomUserDetailsDto(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(()-> {
            return loggedUser.getRole();
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return loggedUser.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedUser.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


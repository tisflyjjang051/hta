package com.jjang051.instagram.dto;

import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private Member loggedMember;
    private Map<String, Object> attributes;

    public CustomUserDetails(Member loggedMember) {
        this.loggedMember = loggedMember;
    }
    public CustomUserDetails(Member loggedMember, Map<String,Object> attributes) {
        this.loggedMember = loggedMember;
        this.attributes = attributes;
    }


    //private final Member loggedMember;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> String.valueOf(loggedMember.getRole()));
        return collection;
    }

    @Override
    public String getPassword() {
        return loggedMember.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedMember.getUserId();
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

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}

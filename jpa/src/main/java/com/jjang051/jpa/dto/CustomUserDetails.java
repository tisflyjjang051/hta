package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Member02;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private Member02 loggedMember;
    private Map<String, Object> attributes;

    public CustomUserDetails(Member02 loggedMember) {

        this.loggedMember = loggedMember;
    }

    public CustomUserDetails(Member02 loggedMember, Map<String, Object> attributes) {
        this.loggedMember = loggedMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return loggedMember.getRole();
            }
        });
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
    public Map<String, Object> getAttributes() {
        return attributes;  // {id:343434343, name:최주호, email:ssarmango@nate.com}
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return (String) attributes.get("name");
    }
}

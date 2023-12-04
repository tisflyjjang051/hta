package com.jjang051.jpa.service;


import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) {
         Optional<Member02> loggedMember = memberRepository.findByUserId(userId);
         if(loggedMember.isPresent()) {
             return new CustomUserDetails(loggedMember.get());
         }
        throw new UsernameNotFoundException("일치하는 회원이 없습니다.");
    }
}

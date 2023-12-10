package com.jjang051.instagram.service;


import com.jjang051.instagram.dto.CustomUserDetails;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> loggedMember = memberRepository.findByUserId(userId); // 6개의 속성을 가진애를
        if(loggedMember.isPresent()) {
            return new CustomUserDetails(loggedMember.get());
        }
        throw new UsernameNotFoundException("아이디 패스워드 확인해 주세요.");
    }
}

package com.jjang051.board.service;

import com.jjang051.board.code.ErrorCode;
import com.jjang051.board.dao.MemberDao;
import com.jjang051.board.dto.CustomUserDetails;
import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String userId) {
         JoinDto loggedMember = memberDao.loginMember(userId);
         if(loggedMember!=null) {
             return new CustomUserDetails(loggedMember);
         }
        throw new UsernameNotFoundException("일치하는 회원이 없습니다.");
    }
}

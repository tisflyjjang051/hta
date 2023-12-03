package com.jjang051.photogram02.service;

import com.jjang051.photogram02.dto.CustomUserDetailsDto;
import com.jjang051.photogram02.entity.User;
import com.jjang051.photogram02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) {
        User userEntity = userRepository.findByUserId(userId);
        if(userEntity!=null) {
            return new CustomUserDetailsDto(userEntity);
        }
        throw new UsernameNotFoundException("일치하는 회원이 없습니다.");
    }

}

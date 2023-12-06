package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.oauth.*;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("여기 서비스 탐");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //log.info("userRequest.getClientRegistration().getRegistrationId()===={}",userRequest.getClientRegistration().getRegistrationId());
        //System.out.println(oauth2User.getAttributes());
        //log.info("oauth2User.getAttributes()===={}",oauth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("google")) {
            log.info("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo( oAuth2User.getAttributes() );
        } else if(provider.equals("kakao")) {
            log.info("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes() );
        } else if(provider.equals("naver")) {
            log.info("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo( (Map)oAuth2User.getAttributes().get("response") );
        } else if(provider.equals("facebook")) {
            log.info("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo( oAuth2User.getAttributes() );
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String userId = provider + "_" + providerId;
        String nickName = oAuth2UserInfo.getName();

        Map<String, Object> userInfo = oAuth2User.getAttributes();

        //String userId = "google_"+(String)userInfo.get("sub");
        String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
        Member02 returnMember = null;

        Optional<Member02> findMember = memberRepository.findByUserId(userId);

        if(findMember.isPresent()) {
            returnMember = findMember.get();
        } else {
            returnMember = Member02.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .nickName(nickName)
                    .role("ROLE_USER")
                    .age(30)
                    .build();
            memberRepository.save(returnMember);
        }
        return new CustomUserDetails(returnMember,oAuth2User.getAttributes());
    }
}

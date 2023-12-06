package com.jjang051.jpa.config;

import com.jjang051.jpa.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/member/join","/member/login","/css/**","/images/**","/js/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((auth)->auth
                                .loginPage("/member/login")
                                .usernameParameter("userId")
                                .loginProcessingUrl("/member/login")
                                .defaultSuccessUrl("/board/list02",true)
                                .permitAll()
                        )
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .csrf((auth)-> auth.disable())

                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/member/login")
//                        .authorizationEndpoint(authorization -> authorization
//                                .baseUri("/login/oauth2/authorization")
//                        )
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2DetailsService)
                        )
                );
                return httpSecurity.build();
    }

}

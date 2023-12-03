package com.jjang051.photogram.config;

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
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
                .authenticated()
                .anyRequest()
                .permitAll())
                .formLogin((auth)->auth
                        .loginPage("/auth/signin")            // get
                        //.usernameParameter("userId")
                        .loginProcessingUrl("/auth/signin")  //post
                        .defaultSuccessUrl("/")
                )
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/board/list")
                        .invalidateHttpSession(true)
                )
                //.csrf((auth)->auth.ignoringRequestMatchers("/mail/**"))
                .csrf((auth)->auth.disable());
        return httpSecurity.build();
    }

}









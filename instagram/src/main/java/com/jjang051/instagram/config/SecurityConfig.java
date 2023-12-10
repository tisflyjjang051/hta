package com.jjang051.instagram.config;

import com.jjang051.instagram.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/auth/join","/auth/login","/css/**","/js/**","/images/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((form)->form
                        .loginPage("/auth/login")   // get
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .loginProcessingUrl("/auth/login")  //post
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .logout((form)->form
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                )
                .oauth2Login((ouath2Login) -> ouath2Login
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userService(oAuth2DetailsService)
                        )
                )
                .csrf((csrf)->  csrf.disable());

                return httpSecurity.build();
    }
}

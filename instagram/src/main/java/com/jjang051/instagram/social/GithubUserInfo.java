package com.jjang051.instagram.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GithubUserInfo implements SocialUserInfo {
    private final Map<String , Object> attibutes;
    @Override
    public String getProvider() {
        return "github";
    }
    @Override
    public String getProviderId() {
        return getProvider()+"_"+attibutes.get("id");
    }
    @Override
    public String getEmail() {
        return "";
    }
    @Override
    public String getName() {
        return (String)attibutes.get("login");
    }
}

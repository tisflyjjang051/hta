package com.jjang051.instagram.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleUserInfo implements SocialUserInfo {
    private final Map<String , Object> attibutes;
    @Override
    public String getProvider() {
        return "google";
    }
    @Override
    public String getProviderId() {
        return getProvider()+"_"+(String)attibutes.get("sub");
    }
    @Override
    public String getEmail() {
        return (String)attibutes.get("email");
    }
    @Override
    public String getName() {
        return (String)attibutes.get("name");
    }
}

package com.green.groupirum.auth.userinfo;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;
    private Map<String, Object> naverResponse;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.naverResponse = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return naverResponse.get("id").toString();
    }

    @Override
    public String getEmail() {
        return naverResponse.get("email").toString();
    }

}

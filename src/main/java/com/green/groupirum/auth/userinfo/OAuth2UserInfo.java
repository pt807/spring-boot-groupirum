package com.green.groupirum.auth.userinfo;

import java.util.Map;

public interface OAuth2UserInfo {

    Map<String, Object> getAttributes();

    String getProviderId();

    String getEmail();

}

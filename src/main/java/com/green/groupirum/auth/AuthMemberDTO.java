package com.green.groupirum.auth;

import com.green.groupirum.auth.userinfo.OAuth2UserInfo;
import com.green.groupirum.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collections;
import java.util.Map;

@Getter
@Setter
public class AuthMemberDTO extends DefaultOAuth2User {

    private Member member;
    private OAuth2UserInfo oAuth2UserInfo;

    public AuthMemberDTO(Map<String, Object> attributes, String nameAttributeKey, Member member, OAuth2UserInfo oAuth2UserInfo) {
        super(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, nameAttributeKey);
        this.member = member;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

}

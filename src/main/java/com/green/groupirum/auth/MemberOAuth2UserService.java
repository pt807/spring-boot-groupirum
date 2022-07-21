package com.green.groupirum.auth;

import com.green.groupirum.auth.userinfo.GoogleUserInfo;
import com.green.groupirum.auth.userinfo.KakaoUserInfo;
import com.green.groupirum.auth.userinfo.NaverUserInfo;
import com.green.groupirum.auth.userinfo.OAuth2UserInfo;
import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.MemberRole;
import com.green.groupirum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String social = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = null;
        if (social.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (social.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else if (social.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String email = oAuth2UserInfo.getEmail();
        String providerId = oAuth2UserInfo.getProviderId();
        String nickname = createNickname(social);

        Member findMember = memberRepository.findByEmail(email).orElse(null);
        if (findMember == null) {
            findMember = Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .nicknameOrig(nickname)
                    .social(social)
                    .providerId(providerId)
                    .role(MemberRole.USER)
                    .build();
            memberRepository.save(findMember);
        }

        /* naver, kakao 중복 이메일 검증 필요 */


        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                oAuth2User.getAttributes(),
                userNameAttributeName,
                findMember,
                oAuth2UserInfo
        );

        return authMemberDTO;
    }

    @Transactional(readOnly = true)
    public String createNickname(String social) {
        Long socialCount = memberRepository.countBySocialIgnoreCase(social); // social 계정 갯수
        long id = socialCount + 1;
        String nickname = social + String.format("%06d", id); // social + 6자리 숫자(ex: 000001)
        while (memberRepository.existsByNickname(nickname)) {
            id++;
            nickname = social + String.format("%06d", id);
        }
        return nickname;
    }

}

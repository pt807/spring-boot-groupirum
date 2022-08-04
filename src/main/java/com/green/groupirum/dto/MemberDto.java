package com.green.groupirum.dto;

import com.green.groupirum.domain.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String email;

    private String nickname;

    private String nicknameOrig;

    private String profileImage;

    private String social;

    private String providerId;

    private MemberRole role;

    @Builder
    public MemberDto(Long id, String email, String nickname, String nicknameOrig, String profileImage, String social, String providerId, MemberRole role) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.nicknameOrig = nicknameOrig;
        this.profileImage = profileImage;
        this.social = social;
        this.providerId = providerId;
        this.role = role;
    }
}

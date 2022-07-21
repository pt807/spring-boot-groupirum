package com.green.groupirum.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 8)
    @Length(min = 2, max = 12)
    private String nickname;

    @Column(updatable = false, nullable = false)
    private String nicknameOrig;

    @Column(nullable = false)
    private String social;

    @Column(updatable = false, nullable = false)
    private String providerId;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String email, String nickname, String nicknameOrig, String social, String providerId, MemberRole role) {
        this.email = email;
        this.nickname = nickname;
        this.nicknameOrig = nicknameOrig;
        this.social = social;
        this.providerId = providerId;
        this.role = role;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}

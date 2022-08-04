package com.green.groupirum.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@DynamicInsert
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

    @ColumnDefault("images/profile_image_default.png")
    private String profileImage;

    @Column(nullable = false)
    private String social;

    @Column(updatable = false, nullable = false)
    private String providerId;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Recruit> recruitList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @Builder
    public Member(String email, String nickname, String nicknameOrig, String profileImage, String social, String providerId, MemberRole role) {
        this.email = email;
        this.nickname = nickname;
        this.nicknameOrig = nicknameOrig;
        this.profileImage = profileImage;
        this.social = social;
        this.providerId = providerId;
        this.role = role;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}

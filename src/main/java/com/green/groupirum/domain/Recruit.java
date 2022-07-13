package com.green.groupirum.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Min(1) @Max(20)
    private int personnel;

    @Column(nullable = false)
    private String contact;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status;

    @ColumnDefault("0")
    private int views;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @Builder
    public Recruit(String title, String content, int personnel, String contact, RecruitStatus status, int views, Member member, Game game, List<Reply> replyList) {
        this.title = title;
        this.content = content;
        this.personnel = personnel;
        this.contact = contact;
        this.status = status;
        this.views = views;
        this.member = member;
        this.game = game;
        this.replyList = replyList;
    }

}

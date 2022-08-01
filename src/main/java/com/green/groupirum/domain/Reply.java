package com.green.groupirum.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Reply> child = new ArrayList<>();

    //연관관계 메서드
    public void setRecruit(Recruit recruit) {
        if (this.recruit != null) {
            this.recruit.getReplyList().remove(this);
        }
        this.recruit = recruit;
        recruit.getReplyList().add(this);
    }

    public void setParent(Reply parent) {
        if (this.parent != null) {
            this.parent.getChild().remove(this);
        }
        this.parent = parent;
        parent.getChild().add(this);
    }

    public void updateReply(String content) {
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Builder
    public Reply(String content, Member member, Recruit recruit, Reply parent) {
        this.content = content;
        this.member = member;
        this.recruit = recruit;
        this.parent = parent;
    }

}

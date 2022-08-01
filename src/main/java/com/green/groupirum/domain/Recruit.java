package com.green.groupirum.domain;

import com.green.groupirum.dto.RecruitForm;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
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
    @Min(1)
    @Max(10)
    private int personnel;

    @Column(nullable = false)
    private String contact;

    @Column
    private String contactAddress;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status;

    @ColumnDefault("0")
    private int views;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    public void updateRecruit(RecruitForm recruitForm) {
        this.title = recruitForm.getTitle();
        this.content = recruitForm.getContent();
        this.personnel = recruitForm.getPersonnel();
        this.contact = recruitForm.getContact();
        this.contactAddress = recruitForm.getContactAddress();
        this.recruitDate = recruitForm.getRecruitDate();
        this.game = recruitForm.getGame();
    }

    @Builder
    public Recruit(Long id, String title, String content, int personnel, String contact, String contactAddress, RecruitStatus status, int views, LocalDate recruitDate, Member member, Game game, List<Reply> replyList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.personnel = personnel;
        this.contact = contact;
        this.contactAddress = contactAddress;
        this.status = status;
        this.views = views;
        this.recruitDate = recruitDate;
        this.member = member;
        this.game = game;
        this.replyList = replyList;
    }
}

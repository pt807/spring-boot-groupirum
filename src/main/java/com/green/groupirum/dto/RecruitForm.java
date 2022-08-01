package com.green.groupirum.dto;

import com.green.groupirum.domain.Game;
import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.Recruit;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecruitForm {
    private Long id;
    private String title;
    private String content;
    private int personnel;
    private String contact;
    private String contactAddress;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitDate;

    private String memberId;
    private Member member;
    private String gameName;
    private Game game;

    public Recruit toEntity() {
        Recruit recruit = Recruit.builder()
                .title(title)
                .content(content)
                .personnel(personnel)
                .contact(contact)
                .contactAddress(contactAddress)
                .recruitDate(recruitDate)
                .game(game)
                .member(member)
                .build();
        return recruit;
    }

    @Builder
    public RecruitForm(Long id, String title, String content, int personnel, String contact, String contactAddress, LocalDateTime createdDate, LocalDateTime modifiedDate, LocalDate recruitDate, Member member, Game game) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.personnel = personnel;
        this.contact = contact;
        this.contactAddress = contactAddress;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.recruitDate = recruitDate;
        this.member = member;
        this.game = game;
    }
}

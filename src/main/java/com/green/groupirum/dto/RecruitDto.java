package com.green.groupirum.dto;

import com.green.groupirum.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecruitDto {

    private Long id;
    private String title;
    private String content;
    private int personnel;
    private String contact;
    private RecruitStatus status;
    private int views;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Member member;
    private Game game;
    private List<Reply> replyList;

    public Recruit toEntity(){
        Recruit recruit = Recruit.builder()
                .title(title)
                .content(content)
                .personnel(personnel)
                .contact(contact)
                .status(status)
                .views(views)
                .build();
        return recruit;
    }

    @Builder
    public RecruitDto(Long id, String title, String content, int personnel, String contact, RecruitStatus status, int views, LocalDateTime createdDate, LocalDateTime modifiedDate, Member member, Game game, List<Reply> replyList){
        this.id = id;
        this.title = title;
        this.content = content;
        this.personnel = personnel;
        this.contact = contact;
        this.status = status;
        this.views = views;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.member = member;
        this.game = game;
        this.replyList = replyList;
    }
}

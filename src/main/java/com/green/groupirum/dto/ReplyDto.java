package com.green.groupirum.dto;

import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class ReplyDto {
    private String content;
    private Member member;
    private String replyNickname;
    private Long memberId;
    private Long recruitId;
    private Long parent;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Reply toEntity() {
        Reply reply = Reply.builder()
                .content(content)
                .build();
        return reply;
    }
}

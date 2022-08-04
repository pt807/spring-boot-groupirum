package com.green.groupirum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private MultipartFile file;

    private Boolean isDeleted;

    private String nickname;

}

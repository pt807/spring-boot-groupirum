package com.green.groupirum.service;

import com.green.groupirum.domain.Recruit;
import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {

    private final RecruitRepository recruitRepository;

    public List<RecruitDto> getRecruitList() {
        List<Recruit> recruitList = recruitRepository.findAll();
        List<RecruitDto> recruitDtoList = new ArrayList<>();

        for (Recruit recruit : recruitList) {
            RecruitDto recruitDto = RecruitDto.builder()
                    .id(recruit.getId())
                    .title(recruit.getTitle())
                    .createdDate(recruit.getCreatedDate())
                    .member(recruit.getMember())
                    .game(recruit.getGame())
                    .views(recruit.getViews())
                    .replyList(recruit.getReplyList())
                    .build();
            recruitDtoList.add(recruitDto);
        }
        return recruitDtoList;
    }
}

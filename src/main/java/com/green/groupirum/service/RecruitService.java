package com.green.groupirum.service;

import com.green.groupirum.domain.Game;
import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.Recruit;
import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.dto.RecruitForm;
import com.green.groupirum.repository.GameRepository;
import com.green.groupirum.repository.MemberRepository;
import com.green.groupirum.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

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

    public Page<Recruit> recruitPage(Pageable pageable) {
        return recruitRepository.findAll(pageable);
    }

    public Page<Recruit> gameNamePage(String gameName, Pageable pageable) {
        return recruitRepository.findAllByGame_Name(gameName, pageable);
    }

    public RecruitDto getRecruit(Long id) {
        Recruit recruit = recruitRepository.findById(id).get();
        RecruitDto recruitDto = RecruitDto.builder()
                .id(recruit.getId())
                .title(recruit.getTitle())
                .createdDate(recruit.getCreatedDate())
                .recruitDate(recruit.getRecruitDate())
                .member(recruit.getMember())
                .game(recruit.getGame())
                .replyList(recruit.getReplyList())
                .personnel(recruit.getPersonnel())
                .contact(recruit.getContact())
                .contactAddress(recruit.getContactAddress())
                .content(recruit.getContent())
                .build();

        return recruitDto;
    }

    @Transactional
    public void saveRecruit(RecruitForm recruitForm) {
        Long memberId = Long.parseLong(recruitForm.getMemberId());
        Member member = memberRepository.findById(memberId).get();
        recruitForm.setMember(member);

        Game game = gameRepository.findByName(recruitForm.getGameName()).get();
        recruitForm.setGame(game);

        recruitRepository.save(recruitForm.toEntity());
    }

    //더티체킹
    @Transactional
    public void updateRecruit(RecruitForm recruitForm) {
        Recruit recruit = recruitRepository.findById(recruitForm.getId()).get();

        Game game = gameRepository.findByName(recruitForm.getGameName()).get();
        recruitForm.setGame(game);

        recruit.updateRecruit(recruitForm);
    }

    @Transactional
    public int updateViews(Long id) {
        return recruitRepository.updateViews(id);
    }

    @Transactional
    public void deleteRecruit(Long id) {
        recruitRepository.deleteById(id);
    }
}

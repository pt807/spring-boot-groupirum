package com.green.groupirum.service;

import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.Recruit;
import com.green.groupirum.domain.Reply;
import com.green.groupirum.dto.ReplyDto;
import com.green.groupirum.repository.MemberRepository;
import com.green.groupirum.repository.RecruitRepository;
import com.green.groupirum.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final RecruitRepository recruitRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveReply(ReplyDto replyDto) {
        Recruit recruit = recruitRepository.findById(replyDto.getRecruitId()).get();
        Member member = memberRepository.findById(replyDto.getMemberId()).get();

        Reply reply = replyDto.toEntity();//?

        reply.setRecruit(recruit);
        if (replyDto.getParent() != null) {
            Reply parentReply = replyRepository.findById(replyDto.getParent()).get();
            reply.setParent(parentReply);
        }
        reply.setMember(member);
        replyRepository.save(reply);
    }

    public List<Reply> getReplyList(Long recruitId) {
        List<Reply> replyList = replyRepository.findAllByRecruitId(recruitId);
        return replyList;
    }

    @Transactional
    public void updateReply(Long id, String content) {
        Reply reply = replyRepository.findById(id).get();

        reply.updateReply(content);
    }

    @Transactional
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}

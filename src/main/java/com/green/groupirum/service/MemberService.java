package com.green.groupirum.service;

import com.green.groupirum.auth.AuthMemberDTO;
import com.green.groupirum.domain.Member;
import com.green.groupirum.dto.MemberDto;
import com.green.groupirum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinOrUpdateMember(Long memberId, MemberDto memberDto, AuthMemberDTO authMemberDTO) {
        Member member = memberRepository.findById(memberId).get();
        member.updateNickname(memberDto.getNickname());
        authMemberDTO.setMember(member);
    }

    public boolean isExistsNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}

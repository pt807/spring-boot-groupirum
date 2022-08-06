package com.green.groupirum.service;

import com.green.groupirum.auth.AuthMemberDTO;
import com.green.groupirum.domain.Member;
import com.green.groupirum.dto.MemberUpdateRequestDto;
import com.green.groupirum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Transactional
    public void updateNickname(Long memberId, String nickname, AuthMemberDTO authMemberDTO) {
        Member member = memberRepository.findById(memberId).get();
        member.updateNickname(nickname);
        authMemberDTO.setMember(member);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto dto, AuthMemberDTO authMemberDTO) {
        Member member = memberRepository.findById(memberId).get();
        if (dto.getIsDeleted()) {    // 프로필 사진 삭제
            member.updateProfileImage("https://groupirum-bucket.s3.ap-northeast-2.amazonaws.com/profile_image_default.png");
        } else if (!dto.getFile().isEmpty()) {   // 프로필 사진 업로드
            String fileName = fileService.fileUpload(dto.getFile());
            member.updateProfileImage(fileName);
        }

        if (!member.getNickname().equals(dto.getNickname())) {    // 닉네임 변경
            member.updateNickname(dto.getNickname());
        }

        authMemberDTO.setMember(member);
    }

    public boolean isExistsNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public Member getMember(String nickname) {
        return memberRepository.findByNickname(nickname).get();
    }

}

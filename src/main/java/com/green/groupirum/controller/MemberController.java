package com.green.groupirum.controller;

import com.green.groupirum.auth.AuthMemberDTO;
import com.green.groupirum.dto.MemberDto;
import com.green.groupirum.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAuthenticated() and (#id == authentication.principal.member.id)")
    @PostMapping("/member/{id}")
    public String joinOrUpdateMember(@PathVariable Long id, MemberDto memberDto, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        memberService.joinOrUpdateMember(id, memberDto, authMemberDTO);
        return "redirect:/";
    }

}

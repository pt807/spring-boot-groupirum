package com.green.groupirum.controller;

import com.green.groupirum.auth.AuthMember;
import com.green.groupirum.auth.AuthMemberDTO;
import com.green.groupirum.domain.Member;
import com.green.groupirum.domain.Recruit;
import com.green.groupirum.domain.Reply;
import com.green.groupirum.dto.MemberUpdateRequestDto;
import com.green.groupirum.service.MemberService;
import com.green.groupirum.service.RecruitService;
import com.green.groupirum.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RecruitService recruitService;
    private final ReplyService replyService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String myPage(@AuthMember Member authMember, Model model) {
        Member member = memberService.getMember(authMember.getId());
        List<Recruit> recruitList = member.getRecruitList().stream()
                .sorted(Comparator.comparing(Recruit::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());
        List<Reply> replyList = member.getReplyList().stream()
                .sorted(Comparator.comparing(Reply::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());

        model.addAttribute(member);
        model.addAttribute(recruitList);
        model.addAttribute(replyList);
        return "myPage";
    }

    @GetMapping("/member/{nickname}/recruits")
    public String memberRecruits(@PathVariable String nickname,
                                 @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                 Model model) {
        Member member = memberService.getMember(nickname);
        Page<Recruit> recruitPage = recruitService.getRecruitByMemberNickname(member.getNickname(), pageable);

        int cnt = (int) recruitPage.getTotalElements();
        int totalPage = Math.max(1, recruitPage.getTotalPages());
        int nowPage = recruitPage.getPageable().getPageNumber() + 1; // == pageable.getPageNumber 현재페이지 가져오기
        int startPage = Math.max(1, nowPage - 5); //((nowPage)/pageBlock) * pageBlock + 1;
        int endPage = Math.min(totalPage, nowPage + 4); //startPage + pageBlock - 1;
        if (endPage > totalPage) endPage = totalPage;  // endPage= totalPage<endPage? totalPage:endPage;

        model.addAttribute("cnt", cnt);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);

        model.addAttribute("recruitPage", recruitPage);
        return "memberRecruits";
    }

    @GetMapping("/member/{nickname}/replies")
    public String memberReplies(@PathVariable String nickname,
                                @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model) {
        Member member = memberService.getMember(nickname);
        Page<Reply> replyPage = replyService.getReplyByMemberNickname(member.getNickname(), pageable);

        int cnt = (int) replyPage.getTotalElements();
        int totalPage = Math.max(1, replyPage.getTotalPages());
        int nowPage = replyPage.getPageable().getPageNumber() + 1; // == pageable.getPageNumber 현재페이지 가져오기
        int startPage = Math.max(1, nowPage - 5); //((nowPage)/pageBlock) * pageBlock + 1;
        int endPage = Math.min(totalPage, nowPage + 4); //startPage + pageBlock - 1;
        if (endPage > totalPage) endPage = totalPage;  // endPage= totalPage<endPage? totalPage:endPage;

        model.addAttribute("cnt", cnt);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);

        model.addAttribute("replyPage", replyPage);
        return "memberReplies";
    }

    @PreAuthorize("isAuthenticated() and (#id == authentication.principal.member.id)")
    @PutMapping("/member/{id}/nickname")
    public String updateNickname(@PathVariable Long id, @RequestParam String nickname, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        memberService.updateNickname(id, nickname, authMemberDTO);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/update")
    public String memberUpdateForm(@AuthMember Member authMember, Model model) {
        Member member = memberService.getMember(authMember.getId());
        model.addAttribute(member);
        return "memberUpdateForm";
    }

    @PreAuthorize("isAuthenticated() and (#id == authentication.principal.member.id)")
    @PutMapping("/member/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute MemberUpdateRequestDto dto, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        memberService.updateMember(id, dto, authMemberDTO);
        return "redirect:/mypage";
    }

}

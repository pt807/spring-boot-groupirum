package com.green.groupirum.controller;

import com.green.groupirum.auth.AuthMember;
import com.green.groupirum.domain.Member;
import com.green.groupirum.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/oauth/login")
    public String showLoginForm(HttpServletRequest request) {
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/oauth/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        return "login";
    }

    @PreAuthorize("isAuthenticated() and (#member.nicknameOrig == #member.nickname)")
    @GetMapping("/oauth/join")
    public String MemberJoinForm(@AuthMember Member member, Model model) {
        model.addAttribute(member);
        return "join";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/oauth/nickname/{nickname}")
    @ResponseBody
    public boolean isExistsNickname(@PathVariable String nickname) {
        return memberService.isExistsNickname(nickname);
    }

}

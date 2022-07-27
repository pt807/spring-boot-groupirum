package com.green.groupirum.auth;

import com.green.groupirum.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

        String uri = "/";

        AuthMemberDTO authMember = (AuthMemberDTO) authentication.getPrincipal();
        Member member = authMember.getMember();
        boolean isNotChanged = member.getNickname().equals(member.getNicknameOrig());
        if (isNotChanged) {
            uri = "/oauth/join";
        } else if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            uri = prevPage;
        }

        redirectStrategy.sendRedirect(request, response, uri);

    }

}

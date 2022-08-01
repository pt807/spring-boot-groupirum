package com.green.groupirum.controller;

import com.green.groupirum.domain.Reply;
import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.dto.RecruitForm;
import com.green.groupirum.service.RecruitService;
import com.green.groupirum.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;
    private final ReplyService replyService;

    @GetMapping("/recruit/{id}")
    public String recruitView(@PathVariable("id") Long id, Model model) {
        RecruitDto recruitDto = recruitService.getRecruit(id);
        List<Reply> replyList = replyService.getReplyList(id);
        recruitService.updateViews(id);
        model.addAttribute("recruit", recruitDto);
        model.addAttribute("replyList", replyList);

        return "recruitView";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recruit/save")
    public String recruitWrite() {
        return "recruit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/recruit/save")
    public String recruitSave(RecruitForm recruitForm) {
        recruitService.saveRecruit(recruitForm);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/recruit/update/{id}")
    public String recruitEdit(@PathVariable Long id, Model model) {
        RecruitDto recruitDto = recruitService.getRecruit(id);
        model.addAttribute("recruit", recruitDto);
        return "recruitUpdate";
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/recruit/{id}")
    public String recruitUpdate(@PathVariable Long id, RecruitForm recruitForm) {
        recruitService.updateRecruit(recruitForm);
        return "redirect:/recruit/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/recruit/{id}")
    public String recruitDelete(@PathVariable Long id) {
        recruitService.deleteRecruit(id);
        return "redirect:/";
    }
}

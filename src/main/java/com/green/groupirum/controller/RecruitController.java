package com.green.groupirum.controller;

import com.green.groupirum.domain.Reply;
import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.service.RecruitService;
import com.green.groupirum.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("recruit", recruitDto);
        model.addAttribute("replyList", replyList);

        return "recruitView";
    }
}

package com.green.groupirum.controller;

import com.green.groupirum.domain.Reply;
import com.green.groupirum.dto.ReplyDto;
import com.green.groupirum.repository.ReplyRepository;
import com.green.groupirum.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/{id}")
    public String getReplies(@PathVariable("id") Long recruitId, Model model) {
        List<Reply> replyList = replyService.getReplyList(recruitId);
        model.addAttribute("replyList", replyList);
        return "replyList";
    }

    @PostMapping("/reply")
    @ResponseBody
    public void saveReply(@RequestBody ReplyDto replyDto) {
        replyService.saveReply(replyDto);
    }

    @PutMapping("/reply/{id}")
    public void updateReply(@PathVariable Long id, @RequestBody ReplyDto replyDto) {
        replyService.updateReply(id, replyDto.getContent());
    }

    @DeleteMapping("/reply/{id}")
    @ResponseBody
    public void deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
    }
}

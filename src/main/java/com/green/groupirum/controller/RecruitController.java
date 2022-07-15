package com.green.groupirum.controller;

import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @GetMapping("/recruit/{id}")
    public String recruitView(@PathVariable("id") Long id, Model model) {
        RecruitDto recruitDto = recruitService.getRecruit(id);
        model.addAttribute("recruit", recruitDto);

        return "recruitView";
    }
}

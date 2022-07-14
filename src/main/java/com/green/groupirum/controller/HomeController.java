package com.green.groupirum.controller;

import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecruitService recruitService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<RecruitDto> recruitDtoList = recruitService.getRecruitList();
        model.addAttribute("recruitList", recruitDtoList);
        return "index";
    }
}

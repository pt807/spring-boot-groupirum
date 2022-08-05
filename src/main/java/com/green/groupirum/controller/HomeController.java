package com.green.groupirum.controller;

import com.green.groupirum.domain.Recruit;
import com.green.groupirum.dto.RecruitDto;
import com.green.groupirum.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecruitService recruitService;

    @GetMapping("/")
    public String homePage(Model model, @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Recruit> recruitList = recruitService.recruitPage(pageable);
        model.addAttribute("recruitList", recruitList);

        return "index";
    }

    @GetMapping("/recruitCard")
    public String recruitCard(Model model, @RequestParam String gameName, @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (gameName.isEmpty()) {
            Page<Recruit> recruitList = recruitService.recruitPage(pageable);
            model.addAttribute("recruitList", recruitList);
        } else {
            Page<Recruit> gameNameList = recruitService.gameNamePage(gameName, pageable);
            model.addAttribute("recruitList", gameNameList);
        }
        return "recruitCard";
    }
}

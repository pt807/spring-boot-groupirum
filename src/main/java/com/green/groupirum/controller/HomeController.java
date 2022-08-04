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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecruitService recruitService;

    @GetMapping("/")
    public String homePage(Model model, @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        List<RecruitDto> recruitDtoList = recruitService.getRecruitList();
//        model.addAttribute("recruitList", recruitDtoList);
        Page<Recruit> recruitList = recruitService.recruitPage(pageable);
        model.addAttribute("recruitList", recruitList);

        return "index";
    }

    @GetMapping("/recruitCard")
    public String recruitCard(Model model, @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        List<RecruitDto> recruitDtoList = recruitService.getRecruitList();
//        model.addAttribute("recruitList", recruitDtoList);
        Page<Recruit> recruitList = recruitService.recruitPage(pageable);
        model.addAttribute("recruitList", recruitList);

        return "recruitCard";
    }

    @GetMapping("/footer")
    public String footer() {
        return "fragments/footer";
    }
}

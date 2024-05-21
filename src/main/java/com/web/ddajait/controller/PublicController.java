package com.web.ddajait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.ddajait.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Tag(name = "Public API", description = "Public API 입니다.")
public class PublicController {
    @Autowired
    UserService userService;

    /*
     *  누구나 접근 가능 
     */

     // Index Page
    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index";
    }

    // Login
    @GetMapping("/loginPage")
    public String loginPage(
            @RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);

        return "public/login";

    }
    

    // @Tag(name = "Public API")
    // @Operation(summary = "회원가입", description = "회원가입 API 입니다. email, nickname, password 는 필수이며, age, gender는 선택사항입니다.")
    // @PostMapping("/join")
    // public String join(@ModelAttribute @Valid UserDto dto,
    //         RedirectAttributes redirectAttributes) throws Exception {
    //     log.info("[PublicController][join] Start");

    //     log.info("[PublicController][join] Start dto : "+dto);
        
    //     userService.joinUser(dto);

    //     // 회원가입이 성공했을 때 인덱스 페이지로 리다이렉션하고 성공 파라미터를 함께 전달
    //     redirectAttributes.addAttribute("success", true);
    //     return "redirect:/loginPage";
    // }

    @GetMapping("/joinPage")
    public String joinPage(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("[PublicController][joinPage] error : " + error);
        model.addAttribute("error", error);
        return "public/join";
    }

    // 팝업 닫음
    @GetMapping("/close")
    public String close() {
        return "close";
    }


}




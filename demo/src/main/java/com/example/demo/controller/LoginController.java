package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Student;
import com.example.demo.services.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@Valid LoginRequest loginRequest, Model model) {
        model.addAttribute("loignAuth",loginService.checkLogin(loginRequest));
        model.addAttribute("logger", loginRequest);
        return "beginning";
    }

    @PostMapping("submit")
    public ModelAndView accountRegistration(@Valid LoginRequest loginRequest, BindingResult result,Model model){
        if (result.hasErrors()) {
            return new ModelAndView("beginning");
        }try {
           
        } catch (Exception e) {
            return new ModelAndView("beginning")
                    .addObject("errorMessage" , e.getMessage());
        }
        return new ModelAndView("main");
    }



}

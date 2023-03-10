package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Student;
import com.example.demo.services.BusinessOrganisationService;
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
    private final BusinessOrganisationService businessOrganisationService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("logger", new LoginRequest());
        return "login";
    }

    @PostMapping("submitlogin")
    public ModelAndView accountRegistration(@Valid LoginRequest loginRequest, BindingResult result, Model model) {
        model.addAttribute("logger", loginRequest);
        if (loginService.checkLogin(loginRequest)){        //redirect to organization
            if (result.hasErrors()) {
                return new ModelAndView("login");
            }
            else {
                return new ModelAndView("stuMain");
            }
        }else {                                             //redirect to student
            if (result.hasErrors()) {
                return new ModelAndView("login");
            }
            else {
                return new ModelAndView("orgMain");
            }
        }
    }
    @GetMapping("/organization")
    public String orgForm(@Valid LoginRequest loginRequest,Model model) {
        model.addAttribute("orgPostsList",businessOrganisationService.getOrgPosts(loginService.getAccountID(loginRequest)));
        return "orgmain";

    }
}

package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Student;
import com.example.demo.services.BusinessOrganisationService;
import com.example.demo.services.LoginService;
import com.example.demo.services.StudentService;
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
public class RegisterController {
    private final StudentService studentService;
    private final BusinessOrganisationService businessOrganisationService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("organization", new BusinessOrganisation());
        return "register";
    }

    @PostMapping("createStudent")
    public ModelAndView createAccount(@Valid Student student, BindingResult result, Model model) {
        model.addAttribute("student", student);

        if (result.hasErrors()) {
            return new ModelAndView("register");
        }
        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            return new ModelAndView("register")
                    .addObject("errorMessage", e.getMessage());
        }
        return new ModelAndView("stuMain");
    }

    @PostMapping("createOrganization")
    public ModelAndView createAccount(@Valid BusinessOrganisation businessOrganisation, BindingResult result, Model model) {
        model.addAttribute("organization", businessOrganisation);

        if (result.hasErrors()) {
            return new ModelAndView("register");
        }
        try {
            businessOrganisationService.createOrganisation(businessOrganisation);
        } catch (Exception e) {
            return new ModelAndView("register")
                    .addObject("errorMessage", e.getMessage());
        }
        return new ModelAndView("orgMain");
    }
}

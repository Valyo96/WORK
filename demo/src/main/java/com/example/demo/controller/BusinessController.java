package com.example.demo.controller;

import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Student;
import com.example.demo.services.BusinessOrganisationService;
import com.example.demo.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessOrganisationService organisationService;
    private final StudentService studentService;

    @GetMapping("/login")
    public String login(BusinessOrganisation businessOrganisation, Student student, Model model){
        model.addAttribute("org",businessOrganisation);
        model.addAttribute("student",student);
        return "beginning";
    }
    @PostMapping("submit")
    public ModelAndView accountRegistration(@Valid BusinessOrganisation businessOrganisation, @Valid Student student,
                                            BindingResult result){
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }try {
            if(businessOrganisation == null){
                studentService.addStudent(student);
            } else {
                organisationService.createOrganisation(businessOrganisation);
            }
        } catch (Exception e) {
            return new ModelAndView("beginning")
                    .addObject("errorMessage" , e.getMessage());
        }
        return new ModelAndView("main");
    }


}

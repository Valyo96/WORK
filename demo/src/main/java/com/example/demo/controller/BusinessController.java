package com.example.demo.controller;

import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.services.BusinessOrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class BusinessController {

    private final BusinessOrganisationService organisationService;

    @GetMapping
    public String login(){
        return "login";
    }


}

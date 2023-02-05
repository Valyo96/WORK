package com.example.demo.services;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.constants.ExceptionMessages.accNotFound;
import static com.example.demo.constants.ExceptionMessages.iDnotFoundExceptionMessage;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StudentService studentService;
    private final BusinessOrganisationService organisationService;

    public boolean checkLogin(LoginRequest login) {
        boolean account = false;
        List<Student> students = studentService.getAll();
        List<BusinessOrganisation> organisations = organisationService.getAllBusinessOrganisations();
        if (students.stream().anyMatch(s -> s.getEmail().equals(login.getEmail()))) {
            account = true;
        } else if (organisations.stream().anyMatch(o -> o.getEmail().equals(login.getEmail()))) {
            account = false;
        } else {
            throw new NotFoundException(accNotFound + login.getEmail());
        }
        return account;
    }

    public long getAccountID(LoginRequest login) {
        long id = 0;
        List<Student> students = studentService.getAll();
        List<BusinessOrganisation> organisations = organisationService.getAllBusinessOrganisations();

        if (checkLogin(login)) {
            for (Student student : students) {
                if (student.getEmail().equals(login.getEmail())) {
                    id = student.getId();
                } else {
                    throw new NotFoundException(iDnotFoundExceptionMessage);
                }
            }
        } else {
            for (BusinessOrganisation organisation : organisations) {
                if (organisation.getEmail().equals(login.getEmail())) {
                    id = organisation.getId();
                } else {
                    throw new NotFoundException(iDnotFoundExceptionMessage);
                }
            }
        }


        return id;
    }
}

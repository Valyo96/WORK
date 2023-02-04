package com.example.demo.services;

import com.example.demo.entity.Student;
import com.example.demo.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student addStudent(Student student){
       return studentRepository.save(student);
    }
}

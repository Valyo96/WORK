package com.example.demo.entity;

import com.example.demo.annotations.EmailExtended;
import com.example.demo.annotations.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

import static com.example.demo.constants.ExceptionMessages.*;
import static com.example.demo.constants.Regex.stringRegexPattern;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "students")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = stringRegexPattern , message = letterValidationMessage)
    @Column(name = "name")
    private String name;
    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = stringRegexPattern , message = letterValidationMessage)
    @Column(name = "last_name")
    private String fName;
    @EmailExtended
    @NotBlank(message = notBlankMessage)
    private String email;
    @Password
    private String password;
    @Min(value = 14 , message = ageValidMessage)
    private Integer age;

    private List<String> categories;

    private HashSet<String> prefLocation;

    private String image;

    private final boolean isUser = true;


}

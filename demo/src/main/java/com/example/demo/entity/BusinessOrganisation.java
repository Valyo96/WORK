package com.example.demo.entity;

import com.example.demo.annotations.EmailExtended;
import com.example.demo.annotations.Password;
import com.example.demo.exceptions.DateInputException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.constants.ExceptionMessages.*;
import static com.example.demo.constants.Regex.stringRegexPattern;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "organisations")
public class BusinessOrganisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = stringRegexPattern , message = letterValidationMessage)
    @Column(name = "name")
    private String name;
    @EmailExtended
    @NotBlank(message = notBlankMessage)
    private String email;
    @Password
    private String password;
    @OneToMany
    private List<Post> posts;

    private LocalDate founded;

    private String location;

    private String image;
    @Column(name = "is_user")
    private final boolean userType = false;


    public void setFounded(LocalDate founded) {
        if(founded.isBefore(LocalDate.now())){
            this.founded = founded;
        } else {
            throw new DateInputException(dateValidMessage);
        }

    }
}

package com.example.demo.entity;

import com.example.demo.constants.PostType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.constants.ExceptionMessages.letterValidationMessage;
import static com.example.demo.constants.ExceptionMessages.notBlankMessage;
import static com.example.demo.constants.Regex.stringRegexPattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = stringRegexPattern , message = letterValidationMessage)
    @Column(name = "name")
    private String name;
    @NotBlank(message = notBlankMessage)
    @Pattern(regexp = stringRegexPattern , message = letterValidationMessage)
    @Column(name = "category")
    private String category;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String location;

    private Integer interestCounter;
    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType type;


}

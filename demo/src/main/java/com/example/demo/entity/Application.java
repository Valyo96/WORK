package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student studentId;
}

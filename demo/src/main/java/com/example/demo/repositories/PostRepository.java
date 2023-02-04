package com.example.demo.repositories;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post>findByDate(LocalDate date);
    Optional<Post> findByCategory(String category);
}
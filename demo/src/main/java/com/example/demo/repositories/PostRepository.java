package com.example.demo.repositories;

import com.example.demo.constants.PostType;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>>findByLocation(String location);

    Optional<Post> findByType(PostType label);
}
package com.example.demo.services;

import com.example.demo.entity.Post;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceHelper {

    private final PostRepository postRepo;

    public List<Post> getPostsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Post> wantedPosts = new ArrayList<>();
        List<Post> posts = postRepo.findAll();
        for (Post post : posts) {
            if (post.getCreatedAt().isAfter(startDate) && post.getCreatedAt().isBefore(endDate)) {
                wantedPosts.add(post);
            }
        }
        return wantedPosts;
    }
}

package com.example.demo.services;

import com.example.demo.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Categories {

    private final PostService postService;

    public HashSet<String> getCategories() {
        HashSet<String> categoryList = new HashSet<>();
        for (String category : getAllCategories()) {
            categoryList.add(category);
        }
        return categoryList;
    }

    private List<String> getAllCategories(){
        List<Post> posts = postService.getAll();
        List<String> categories = new ArrayList<>();
        for (Post post : posts) {
           categories.add(post.getCategory());
        }
        return categories;
    }

}
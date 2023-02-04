package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class Categories {
    public HashSet<String> getCategories(List<String> categories) {
        HashSet<String> categoryList = new HashSet<>();
        for (String category : categories) {
            categoryList.add(category);
        }
        return categoryList;
    }
}
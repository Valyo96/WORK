package com.example.demo.services;

import com.example.demo.entity.Application;
import com.example.demo.entity.Post;
import com.example.demo.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository appRepository;
    public List<Application> getAll (){
        return appRepository.findAll();
    }

    public void deleteAllAppliesConnectedToPostById(Long postId){
        List<Application> apps = getAll().stream().filter(a -> a.getPostId().getId() == postId).collect(Collectors.toList());
        appRepository.deleteAll(apps);
    }
}

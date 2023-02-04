package com.example.demo.services;

import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Post;
import com.example.demo.repositories.BusinessOrganisationRepository;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAll(){
        return postRepository.findAll();
    }



    public void deleteAllPostsConnectedToOrganisationById(Long organisationId){

        List<Post> posts = getAll().stream().filter(p -> p.getOrganisation().getId() == organisationId).collect(Collectors.toList());
        postRepository.deleteAll(posts);
            }

}

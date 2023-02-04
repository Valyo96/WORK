package com.example.demo.services;

import com.example.demo.entity.Post;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.PostRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.constants.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostServiceHelper serviceHelper;
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(iDnotFoundExceptionMessage));
    }

    public List<Post> findByCategory(String category){
        List<Post> posts = getAll().stream().filter(p -> p.getCategory().equals(category)).collect(Collectors.toList());
        return posts;
    }

    public List<Post> findByDate(LocalDate startDate , LocalDate endDate){
        if(serviceHelper.getPostsByDateRange(startDate , endDate).isEmpty()){
            throw new NoResultException(emptyResult);
        }
        return serviceHelper.getPostsByDateRange(startDate , endDate);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public void deletePostById(Long postId){
        Post post = findById(postId);
        postRepository.delete(post);
    }

    public void deleteAllPostsConnectedToOrganisationById(Long organisationId){

        List<Post> posts = getAll().stream().filter(p -> p.getOrganisation().getId() == organisationId).collect(Collectors.toList());
        postRepository.deleteAll(posts);
            }

}

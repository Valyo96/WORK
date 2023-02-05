package com.example.demo.services;

import com.example.demo.constants.PostType;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Post;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.BusinessOrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.constants.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class BusinessOrganisationService {

    private final BusinessOrganisationRepository businessRepository;

    private final PostService postService;

    private final  ApplicationService aService;
private final StudentService studentService;

    public List<BusinessOrganisation> getAllBusinessOrganisations(){
        return businessRepository.findAll();
    }

    public BusinessOrganisation getById(Long id){
        return businessRepository.findById(id).orElseThrow(() -> new NotFoundException(iDnotFoundExceptionMessage));
    }

    public BusinessOrganisation findByName(String name){
        return businessRepository.findByName(name).orElseThrow(() -> new NotFoundException(nameNotFoundExceptionMessage));
    }

    public BusinessOrganisation updateOrganisation(BusinessOrganisation organisation){
        List<BusinessOrganisation> organisations = getAllBusinessOrganisations();
        List<BusinessOrganisation> org = new ArrayList<>();
        for(BusinessOrganisation o: organisations){
            if(!organisation.getEmail().equals(o.getEmail())){
                org.add(o);
            }
        }
        if(org.stream().anyMatch(o -> o.getEmail().equals(organisation.getEmail()))){
            throw new AlreadyExistException(emailOrganisationAlreadyExists);
        }
      return   businessRepository.save(organisation);
    }

    public void deleteOrganisation(Long organisationId){
        postService.deleteAllPostsConnectedToOrganisationById( organisationId);
        businessRepository.deleteById( organisationId);
    }

    public void deletePostById(Long organisationId, Long postId){
        Post post = postService.getAll().stream().filter(p -> p.getOrganisation().getId() == organisationId && p.getId() == postId).findFirst().orElseThrow(() -> new NotFoundException(iDnotFoundExceptionMessage));
        aService.deleteAllAppliesConnectedToPostById(postId);
        postService.deletePostById(post.getId());
    }

    public List<Post> findPostByDate(LocalDate startDate , LocalDate endDate){
        return postService.findByDate(startDate, endDate);
    }

    public List<Post> getPostsByCategory(String category){
        return postService.findByCategory(category);
    }

    public Post findByPostType(PostType label){
        return postService.findByPostType(label);
    }


    public BusinessOrganisation createOrganisation(BusinessOrganisation organisation){
        if(getAllBusinessOrganisations().stream().anyMatch( b ->b.getEmail().equals(organisation.getEmail()))&&
                studentService.getAll().stream().anyMatch(s-> s.getEmail().equals(organisation.getEmail()))){
            throw new AlreadyExistException(emailAlreadyRegistered+organisation.getEmail());
        }

        return   businessRepository.save(organisation);
    }
    public List<Post> getOrgPosts(Long orgId) {

        List<Post> posts = postService.getAll().stream()
                .filter(p -> p.getOrganisation().getId() == orgId)
                .collect(Collectors.toList());
        return posts;

    }
}

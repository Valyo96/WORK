package com.example.demo.services;

import com.example.demo.constants.PostType;
import com.example.demo.entity.BusinessOrganisation;
import com.example.demo.entity.Post;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.BusinessOrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.constants.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class BusinessOrganisationService {

    private final BusinessOrganisationRepository businessRepository;

    private final PostService postService;


    public List<BusinessOrganisation> getAllBusinessOrganisations(){
        return businessRepository.findAll();
    }

    public BusinessOrganisation getById(Long id){
        return businessRepository.findById(id).orElseThrow(() -> new NotFoundException(iDnotFoundExceptionMessage));
    }

    public BusinessOrganisation findByName(String name){
        return businessRepository.findByName(name).orElseThrow(() -> new NotFoundException(nameNotFoundExceptionMessage));
    }


    public BusinessOrganisation createOrganisation(BusinessOrganisation organisation){
        if(getAllBusinessOrganisations().stream().anyMatch( b ->b.getEmail().equals(organisation.getEmail()))){
            throw new AlreadyExistException(organisationAlreadyExistMessage+organisation.getEmail());
        }

          return   businessRepository.save(organisation);
    }

    public BusinessOrganisation updateOrganisation(BusinessOrganisation organisation){
        if(getAllBusinessOrganisations().stream().anyMatch(b -> b.getEmail().equals(organisation.getEmail()))){
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
        postService.deletePostById(post.getId());
    }
}

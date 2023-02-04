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

    public BusinessOrganisation findByType(PostType label){
        return businessRepository.findByType(label).orElseThrow(() -> new NotFoundException(postTypeNotFoundMessage));
    }

    public BusinessOrganisation createOrganisation(BusinessOrganisation organisation){
        if(getAllBusinessOrganisations().stream().anyMatch( b ->b.equals(organisation))){
            throw new AlreadyExistException(organisationAlreadyExistMessage+organisation.getName());
        }

          return   businessRepository.save(organisation);
    }

    public BusinessOrganisation updateOrganisation(BusinessOrganisation organisation){
        if(getAllBusinessOrganisations().stream().anyMatch(b -> b.getEmail().equals(organisation.getEmail()))){
            throw new AlreadyExistException(emailOrganisationAlreadyExists);
        }
      return   businessRepository.save(organisation);
    }

    public void deleteOrganisation(Long id){
        postService.deleteAllPostsConnectedToOrganisationById(id);
        businessRepository.deleteById(id);
    }
}

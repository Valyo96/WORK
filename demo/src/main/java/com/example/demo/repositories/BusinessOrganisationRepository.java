package com.example.demo.repositories;

import com.example.demo.constants.PostType;
import com.example.demo.entity.BusinessOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessOrganisationRepository extends JpaRepository<BusinessOrganisation, Long> {
    Optional<BusinessOrganisation> findByName(String name);
    Optional<BusinessOrganisation> findByType(PostType label);


}

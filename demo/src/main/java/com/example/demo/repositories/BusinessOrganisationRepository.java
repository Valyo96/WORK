package com.example.demo.repositories;

import com.example.demo.entity.BusinessOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessOrganisationRepository extends JpaRepository<BusinessOrganisation, Long> {
}
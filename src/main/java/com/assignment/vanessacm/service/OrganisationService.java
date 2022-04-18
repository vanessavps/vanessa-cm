package com.assignment.vanessacm.service;

import com.assignment.vanessacm.entity.Organisation;
import com.assignment.vanessacm.repository.OrganisationRepository;
import com.assignment.vanessacm.utils.ObjectCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {
    private final OrganisationRepository organisationRepository;

    @Autowired
    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public Organisation update(Integer id, Organisation organisation) {
        Organisation existentOrganisation = this.findById(id);
        if (existentOrganisation != null) {
            ObjectCopyUtils.copyNonNullProperties(organisation, existentOrganisation);
            return this.save(existentOrganisation);
        }
        return null;
    }

    public List<Organisation> findAll() {
        return organisationRepository.findAll();
    }

    public Organisation findById(Integer id) {
        return organisationRepository.findById(id)
                                     .orElse(null);
    }

    public void deleteById(Integer id) {
        organisationRepository.deleteById(id);
    }
}

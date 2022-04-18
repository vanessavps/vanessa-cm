package com.assignment.vanessacm.service;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.entity.Organisation;
import com.assignment.vanessacm.repository.OrganisationRepository;
import com.assignment.vanessacm.utils.ObjectCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrganisationService {
    private final ContactService contactService;
    private final OrganisationRepository organisationRepository;


    @Autowired
    public OrganisationService(ContactService contactService, OrganisationRepository organisationRepository) {
        this.contactService = contactService;
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

    public Contact addContact(Integer organisationId, Contact newContact) {
        return organisationRepository.findById(organisationId)
                                     .map(organisation -> addNewOrExistentContact(newContact, organisation))
                                     .orElse(null);
    }

    public List<Contact> findContactsByOrganisationId(Integer organisationId) {
        if (!organisationRepository.existsById(organisationId)) {
            return Collections.emptyList();
        }
        return contactService.findContactsByOrganisationId(organisationId);
    }

    public void deleteContactFromOrganisation(Integer organisationId, Integer contactId) {
        Organisation organisation = this.findById(organisationId);
        if (organisation == null) {
            return;
        }

        organisation.removeContact(contactId);
        this.update(organisationId, organisation);
    }

    private Contact addNewOrExistentContact(Contact newContact, Organisation organisation) {
        Integer contactId = newContact.getId();
        if (contactId != null) {
            Contact existentContact = contactService.findById(contactId);
            if (existentContact != null) {
                organisation.addContact(existentContact);
                this.update(organisation.getId(), organisation);
                return existentContact;
            }
        }

        organisation.addContact(newContact);
        return contactService.save(newContact);
    }


}

package com.assignment.vanessacm.service;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.entity.Organisation;
import com.assignment.vanessacm.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrganisationService extends CrudService<Organisation> {
    private final ContactService contactService;

    @Autowired
    public OrganisationService(ContactService contactService, OrganisationRepository organisationRepository) {
        super(organisationRepository);
        this.contactService = contactService;
    }

    public Contact addContact(Integer organisationId, Contact newContact) {
        return this.repository.findById(organisationId)
                              .map(organisation -> addNewOrExistentContact(newContact, organisation))
                              .orElse(null);
    }

    public List<Contact> findContactsByOrganisationId(Integer organisationId) {
        if (!this.repository.existsById(organisationId)) {
            return Collections.emptyList();
        }
        return contactService.findContactsByOrganisationId(organisationId);
    }

    public void deleteContactFromOrganisation(Integer organisationId, Integer contactId) {
        Organisation organisation = this.findById(organisationId);
        if (organisation != null) {
            organisation.removeContact(contactId);
            this.update(organisationId, organisation);
        }
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

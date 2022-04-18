package com.assignment.vanessacm.service;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService extends CrudService<Contact> {

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        super(contactRepository);
    }

    public List<Contact> findContactsByOrganisationId(Integer organisationId) {
        ContactRepository contactRepository = (ContactRepository) this.repository;
        return contactRepository.findContactsByOrganisationsId(organisationId);
    }
}

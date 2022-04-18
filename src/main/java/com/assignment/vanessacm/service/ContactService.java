package com.assignment.vanessacm.service;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.repository.ContactRepository;
import com.assignment.vanessacm.utils.ObjectCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, Contact contact) {
        Contact existentContact = this.findById(id);
        if (existentContact != null) {
            ObjectCopyUtils.copyNonNullProperties(contact, existentContact);
            return this.save(existentContact);
        }
        return null;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(Integer id) {
        return contactRepository.findById(id)
                                .orElse(null);
    }

    public void deleteById(Integer id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> findContactsByOrganisationId(Integer organisationId) {
        return contactRepository.findContactsByOrganisationsId(organisationId);
    }
}

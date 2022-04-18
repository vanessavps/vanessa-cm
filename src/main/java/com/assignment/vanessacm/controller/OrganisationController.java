package com.assignment.vanessacm.controller;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.entity.Organisation;
import com.assignment.vanessacm.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {
    private final OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @PostMapping
    public ResponseEntity<Organisation> create(@RequestBody Organisation organisation) {
        Organisation newOrganisation = organisationService.save(organisation);
        return new ResponseEntity<>(newOrganisation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Organisation>> getAll() {
        List<Organisation> organisations = organisationService.findAll();
        if (organisations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(organisations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organisation> getById(@PathVariable Integer id) {
        Organisation organisation = organisationService.findById(id);
        if (organisation != null) {
            return new ResponseEntity<>(organisation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
        organisationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organisation> update(@PathVariable Integer id, @RequestBody Organisation organisation) {
        Organisation updatedOrganisation = organisationService.update(id, organisation);
        if (updatedOrganisation != null) {
            return new ResponseEntity<>(updatedOrganisation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*********************************
     Manage organisation contacts
     *********************************/
    @PostMapping("/{id}/contact")
    public ResponseEntity<Contact> addContact(@PathVariable Integer id, @RequestBody Contact contact) {
        Contact newContact = organisationService.addContact(id, contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    // Manage organisation contacts
    @GetMapping("/{id}/contact")
    public ResponseEntity<List<Contact>> getContactsByOrganisationId(@PathVariable Integer id) {
        List<Contact> contacts = organisationService.findContactsByOrganisationId(id);
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/contact/{contactId}")
    public ResponseEntity<HttpStatus> deleteContactFromOrganisation(@PathVariable Integer id,
                                                                    @PathVariable Integer contactId) {
        organisationService.deleteContactFromOrganisation(id, contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

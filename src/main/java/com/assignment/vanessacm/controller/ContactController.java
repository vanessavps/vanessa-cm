package com.assignment.vanessacm.controller;

import com.assignment.vanessacm.entity.Contact;
import com.assignment.vanessacm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        Contact newContact = contactService.save(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        List<Contact> contacts = contactService.findAll();
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Integer id) {
        Contact contact = contactService.findById(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        contactService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Integer id, @RequestBody Contact contact) {
        Contact updatedContact = contactService.update(id, contact);
        if (updatedContact != null) {
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.assignment.vanessacm.repository;

import com.assignment.vanessacm.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findContactsByOrganisationsId(Integer organisationId);
}

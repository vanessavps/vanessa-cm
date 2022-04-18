package com.assignment.vanessacm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;
    private String address;
    private String suburb;
    private String city;
    private String postcode;
    private String country;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "organisation_contacts", joinColumns = {
            @JoinColumn(name = "organisation_id")}, inverseJoinColumns = {@JoinColumn(name = "contact_id")})
    @JsonIgnore
    private Set<Contact> contacts = new HashSet<>();

    public Organisation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
        contact.getOrganisations()
               .add(this);
    }

    public void removeContact(Integer contactId) {
        Contact contact = this.contacts.stream()
                                       .filter(c -> c.getId().equals(contactId))
                                       .findFirst()
                                       .orElse(null);
        if (contact != null) {
            this.contacts.remove(contact);
            contact.getOrganisations().remove(this);
        }

    }
}

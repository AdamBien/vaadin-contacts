package com.vaadin.contacts.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 * ContactServiceImpl simulates stateless contact service by storing contacts in
 * memory (instead of persisting them)
 */
@Stateful
@SessionScoped
public class ContactServiceImpl implements ContactService {

    private static final int NUMBER_OF_CONTACTS = 20;
    private Random random;
    private List<Contact> contacts;

    public ContactServiceImpl() {
        contacts = new ArrayList<Contact>();

        random = new Random();
        randomizeContacts();
    }

    @Override
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    @Override
    public void storeContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }

        // Persist contact here
    }

    @Override
    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    private void randomizeContacts() {
        for (int i = 0; i < NUMBER_OF_CONTACTS; i++) {
            Contact contact = new Contact();
            contact.setFirstName(generateFirstName());
            contact.setLastName(generateLastName());
            contact.setCity(generateCity());
            contact.setZipCode(generatePostalCode());
            contact.setComment(generateComment());
            contacts.add(contact);
        }
    }

    private String generateComment() {
        return "";
    }

    private String generateCity() {
        String[] cities = {"Sydney", "Berlin", "Helsinki", "Rabbat", "Zuerich",
            "Chicago", "London", "New York", "Raleigh", "Durham",
            "Los Angeles", "Munich"};

        return cities[random.nextInt(cities.length)];
    }

    private String generatePostalCode() {
        String[] postalCodes = {"20456", "23463", "68544", "23684", "65478",
            "87544", "34684", "10495", "07346", "35730", "34685", "85739"};

        return postalCodes[random.nextInt(postalCodes.length)];
    }

    private String generateLastName() {
        String[] lastNames = {"Hume", "Alpert", "Austen", "Fernandez",
            "Jarrah", "Kwon", "Lapidus", "Littleton", "Linus", "Ford",
            "Lloyd", "Cortez"};

        return lastNames[random.nextInt(lastNames.length)];
    }

    private String generateFirstName() {
        String[] lastNames = {"Ana Lucia", "John", "Richard", "Jack",
            "Claire", "Kate", "James", "James", "Libby", "Hurley", "Boone",
            "Juliet"};

        return lastNames[random.nextInt(lastNames.length)];
    }

    @Override
    public Contact createContact() {
        return new Contact();
    }
}

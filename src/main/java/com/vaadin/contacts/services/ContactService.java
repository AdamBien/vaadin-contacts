package com.vaadin.contacts.services;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ContactService {

	public List<Contact> getContacts();

	public void storeContact(Contact contact);

	public void removeContact(Contact contact);

	public Contact createContact();
}

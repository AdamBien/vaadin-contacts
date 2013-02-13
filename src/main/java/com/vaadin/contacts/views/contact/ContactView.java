package com.vaadin.contacts.views.contact;

import java.util.List;

import com.vaadin.contacts.services.Contact;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;

public interface ContactView extends View {

	public void populateContacts(List<Contact> contacts);

	public void editContact(BeanItem<Contact> selectedContact);

	public void clearContactEditor();
}

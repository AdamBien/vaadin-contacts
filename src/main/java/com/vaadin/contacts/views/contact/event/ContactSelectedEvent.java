package com.vaadin.contacts.views.contact.event;

import com.vaadin.contacts.services.Contact;
import com.vaadin.data.util.BeanItem;

public class ContactSelectedEvent {

	private BeanItem<Contact> selectedContact;

	public ContactSelectedEvent(BeanItem<Contact> selectedContact) {
		this.selectedContact = selectedContact;
	}

	public BeanItem<Contact> getSelectedContact() {
		return selectedContact;
	}
}

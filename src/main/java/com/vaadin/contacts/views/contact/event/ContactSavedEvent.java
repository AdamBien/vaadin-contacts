package com.vaadin.contacts.views.contact.event;

import com.vaadin.contacts.services.Contact;

public class ContactSavedEvent {

	private Contact contact;

	public ContactSavedEvent(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}
}

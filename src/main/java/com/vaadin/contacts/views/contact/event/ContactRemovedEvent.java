package com.vaadin.contacts.views.contact.event;

import com.vaadin.contacts.services.Contact;

public class ContactRemovedEvent {

	private Contact contact;

	public ContactRemovedEvent(Contact contact) {
		this.contact = contact;
	}

	public Contact getContact() {
		return contact;
	}
}

package com.vaadin.contacts.views.contact;

import javax.enterprise.event.Observes;

import com.vaadin.contacts.services.Contact;
import com.vaadin.contacts.services.ContactService;
import com.vaadin.contacts.views.contact.event.ContactCreatedEvent;
import com.vaadin.contacts.views.contact.event.ContactRemovedEvent;
import com.vaadin.contacts.views.contact.event.ContactSavedEvent;
import com.vaadin.contacts.views.contact.event.ContactSelectedEvent;
import com.vaadin.data.util.BeanItem;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

public class ContactPresenter {

    private static final long serialVersionUID = -9009785741381662646L;
    @Inject
    ContactService contactService;
    ContactView view;

    @Inject
    public ContactPresenter(ContactView view) {
        this.view = view;
    }

    @PostConstruct
    public void viewInitialized() {
        this.view.populateContacts(contactService.getContacts());
    }

    protected void onContactSaved(
            @Observes ContactSavedEvent contactSaved) {
        contactService.storeContact(contactSaved.getContact());
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }

    protected void onContactSelected(
            @Observes ContactSelectedEvent contactSelected) {
        this.view.editContact(contactSelected.getSelectedContact());
    }

    protected void onContactCreated(
            @Observes ContactCreatedEvent contactCreated) {
        Contact newContact = contactService.createContact();
        this.view.editContact(new BeanItem<>(newContact));
    }

    protected void onContactRemoved(
            @Observes ContactRemovedEvent contactRemoved) {
        contactService.removeContact(contactRemoved.getContact());
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }

    public void viewEntered() {
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }
}

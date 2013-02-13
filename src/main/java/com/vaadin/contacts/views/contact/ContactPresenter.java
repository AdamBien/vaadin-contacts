package com.vaadin.contacts.views.contact;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import com.vaadin.contacts.services.Contact;
import com.vaadin.contacts.services.ContactService;
import com.vaadin.contacts.views.AbstractPresenter;
import com.vaadin.contacts.views.contact.event.ContactCreatedEvent;
import com.vaadin.contacts.views.contact.event.ContactRemovedEvent;
import com.vaadin.contacts.views.contact.event.ContactSavedEvent;
import com.vaadin.contacts.views.contact.event.ContactSelectedEvent;
import com.vaadin.data.util.BeanItem;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ContactPresenter extends AbstractPresenter<ContactView> implements
        Serializable {

    private static final long serialVersionUID = -9009785741381662646L;
    @EJB
    private ContactService contactService;

    public void viewInitialized(ContactView view) {
        setView(view);

        getView().populateContacts(contactService.getContacts());
    }

    protected void onContactSaved(
            @Observes(notifyObserver = Reception.IF_EXISTS) ContactSavedEvent contactSaved) {
        contactService.storeContact(contactSaved.getContact());
        getView().populateContacts(contactService.getContacts());
        getView().clearContactEditor();
    }

    protected void onContactSelected(
            @Observes(notifyObserver = Reception.IF_EXISTS) ContactSelectedEvent contactSelected) {
        getView().editContact(contactSelected.getSelectedContact());
    }

    protected void onContactCreated(
            @Observes(notifyObserver = Reception.IF_EXISTS) ContactCreatedEvent contactCreated) {
        Contact newContact = contactService.createContact();
        getView().editContact(new BeanItem<Contact>(newContact));
    }

    protected void onContactRemoved(
            @Observes(notifyObserver = Reception.IF_EXISTS) ContactRemovedEvent contactRemoved) {
        contactService.removeContact(contactRemoved.getContact());
        getView().populateContacts(contactService.getContacts());
        getView().clearContactEditor();
    }

    @Override
    public void viewEntered() {
        getView().populateContacts(contactService.getContacts());
        getView().clearContactEditor();
    }
}

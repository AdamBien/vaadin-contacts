package com.vaadin.contacts.presentation.contact;

import com.vaadin.cdi.VaadinView;
import com.vaadin.contacts.InstanceTracker;

import com.vaadin.contacts.business.contactstore.entity.Contact;
import com.vaadin.contacts.business.contactstore.boundary.ContactService;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Interceptors(InstanceTracker.class)
@RequestScoped
@VaadinView("main")
public class ContactPresenter implements View, Presenter {

    private static final long serialVersionUID = -9009785741381662646L;
    @Inject
    ContactService contactService;
    @Inject
    ContactView view;

    @PostConstruct
    public void viewInitialized() {
        this.view.populateContacts(contactService.getContacts());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }

    public void contactSaved(
            Contact contactSaved) {
        contactService.storeContact(contactSaved);
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }

    public void contactSelected(BeanItem<Contact> contactSelected) {
        this.view.editContact(contactSelected);
    }

    public void contactCreated() {
        Contact newContact = contactService.createContact();
        this.view.editContact(new BeanItem<>(newContact));
    }

    public void contactRemoved(Contact contactRemoved) {
        contactService.removeContact(contactRemoved);
        this.view.populateContacts(contactService.getContacts());
        this.view.clearContactEditor();
    }

    @Override
    public Component getView() {
        return this.view;
    }
}

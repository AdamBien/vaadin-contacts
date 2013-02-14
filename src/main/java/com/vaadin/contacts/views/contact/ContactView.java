package com.vaadin.contacts.views.contact;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.VaadinView;
import com.vaadin.contacts.services.Contact;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalSplitPanel;

@VaadinView("main")
public class ContactView extends CustomComponent implements View {

    private static final long serialVersionUID = 3377127145341022124L;
    @Inject
    private ContactListTable contactTable;
    @Inject
    private ContactEditorForm editorForm;
    @Inject
    private ContactPresenter presenter;
    private VerticalSplitPanel verticalSplit;

    public ContactView() {
        System.out.println("New instance of " + this);
        setSizeFull();

        verticalSplit = new VerticalSplitPanel();
        verticalSplit.setSizeFull();

        setCompositionRoot(verticalSplit);
    }

    @PostConstruct
    protected void init() {
        verticalSplit.setFirstComponent(contactTable);
        verticalSplit.setSecondComponent(editorForm);
    }

    public void populateContacts(List<Contact> contacts) {
        BeanItemContainer<Contact> container = new BeanItemContainer<>(
                Contact.class);
        container.addAll(contacts);

        contactTable.setContainerDatasource(container);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        presenter.viewEntered();
    }

    public void editContact(BeanItem<Contact> selectedContact) {
        editorForm.populateContact(selectedContact);
    }

    public void clearContactEditor() {
        editorForm.clear();
    }
}

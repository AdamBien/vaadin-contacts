package com.vaadin.contacts.presentation.contact;

import com.vaadin.contacts.InstanceTracker;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.contacts.business.contactstore.entity.Contact;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalSplitPanel;
import javax.interceptor.Interceptors;

@Interceptors(InstanceTracker.class)
public class ContactView extends CustomComponent {

    private static final long serialVersionUID = 3377127145341022124L;
    @Inject
    private ContactListTable contactTable;
    @Inject
    private ContactEditorForm editorForm;
    private VerticalSplitPanel verticalSplit;

    @PostConstruct
    protected void init() {
        setSizeFull();

        verticalSplit = new VerticalSplitPanel();
        verticalSplit.setSizeFull();

        setCompositionRoot(verticalSplit);
        verticalSplit.setFirstComponent(contactTable);
        verticalSplit.setSecondComponent(editorForm);
    }

    public void populateContacts(List<Contact> contacts) {
        BeanItemContainer<Contact> container = new BeanItemContainer<>(
                Contact.class);
        container.addAll(contacts);

        contactTable.setContainerDatasource(container);
    }

    public void editContact(BeanItem<Contact> selectedContact) {
        editorForm.populateContact(selectedContact);
    }

    public void clearContactEditor() {
        editorForm.clear();
    }
}

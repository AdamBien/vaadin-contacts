/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.contacts.presentation.contact;

import com.vaadin.contacts.business.contactstore.boundary.ContactService;
import com.vaadin.contacts.business.contactstore.entity.Contact;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author adam-bien.com
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactPresenterTest {

    @Mock
    ContactService service;
    @Mock
    ContactView view;
    @InjectMocks
    ContactPresenter cut = new ContactPresenter();

    @Test
    public void contactSaved() {
        Contact contact = new Contact();
        cut.contactSaved(contact);
        verify(service).storeContact(contact);
        verify(service).getContacts();
        verify(view).clearContactEditor();
    }
}

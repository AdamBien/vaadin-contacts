package com.vaadin.contacts;

import com.vaadin.contacts.views.contact.ContactView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

public class ContactsViewDisplay extends CustomComponent implements ViewDisplay {

    private static final long serialVersionUID = -8477213635492997536L;

    public ContactsViewDisplay() {
        setSizeFull();
    }

    @Override
    public void showView(View view) {
        setCompositionRoot(((ContactView) view).getComponent());
    }
}

package com.vaadin.contacts.views;

import javax.enterprise.context.SessionScoped;

import com.vaadin.cdi.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

@VaadinView("config")
public class ConfigView extends CustomComponent implements View {

    private static final long serialVersionUID = -1471318787899038223L;

    @Override
    public void enter(ViewChangeEvent event) {
    }
}

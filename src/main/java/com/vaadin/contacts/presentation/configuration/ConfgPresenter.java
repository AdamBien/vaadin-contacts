package com.vaadin.contacts.presentation.configuration;

import com.vaadin.cdi.VaadinView;
import com.vaadin.contacts.InstanceTracker;
import com.vaadin.contacts.presentation.contact.Presenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 *
 * @author adam-bien.com
 */
@VaadinView("config")
@Interceptors(InstanceTracker.class)
public class ConfgPresenter implements Presenter, View {

    @Inject
    ConfigView configView;

    @Override
    public Component getView() {
        return this.configView;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("--initialize!");
    }
}

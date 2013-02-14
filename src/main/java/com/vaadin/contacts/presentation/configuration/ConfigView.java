package com.vaadin.contacts.presentation.configuration;

import com.vaadin.contacts.InstanceTracker;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;

@Interceptors(InstanceTracker.class)
public class ConfigView extends CustomComponent {

    @PostConstruct
    public void initializeUI() {
        setSizeFull();
        Label label = new Label("Number of Instances: " + InstanceTracker.report());
        this.setCompositionRoot(label);
    }
}

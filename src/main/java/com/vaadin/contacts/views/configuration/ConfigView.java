package com.vaadin.contacts.views.configuration;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import javax.annotation.PostConstruct;

public class ConfigView extends CustomComponent {

    @PostConstruct
    public void initializeUI() {
        setSizeFull();
        Label label = new Label("Configuration");
        this.setCompositionRoot(label);
    }
}

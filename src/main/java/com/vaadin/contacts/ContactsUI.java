package com.vaadin.contacts;

import javax.inject.Inject;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.Root;
import com.vaadin.cdi.VaadinUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@VaadinUI
@Root
@PreserveOnRefresh
public class ContactsUI extends UI {

    private static final long serialVersionUID = 1960302824189438305L;
    @Inject
    private CDIViewProvider viewProvider;
    private ContactsViewDisplay viewDisplay;
    private Navigator navigator;
    private Button.ClickListener menuButtonClickListener = new Button.ClickListener() {
        private static final long serialVersionUID = -4375234319752603089L;

        @Override
        public void buttonClick(ClickEvent event) {
            navigator.navigateTo(event.getButton().getData().toString());
        }
    };

    @Override
    public void init(VaadinRequest request) {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSplitPosition(150, Unit.PIXELS);
        splitPanel.setSizeFull();

        setContent(splitPanel);

        viewDisplay = new ContactsViewDisplay();

        navigator = new Navigator(this, viewDisplay);
        navigator.addProvider(viewProvider);

        splitPanel.setFirstComponent(buildMenu());
        splitPanel.setSecondComponent(viewDisplay);

        navigator.navigateTo("main");
    }

    private VerticalLayout buildMenu() {
        VerticalLayout menu = new VerticalLayout();

        menu.addComponent(createMenuButton("Addresses", "main"));
        menu.addComponent(createMenuButton("Configuration", "config"));

        return menu;
    }

    private Button createMenuButton(String caption, String fragment) {
        Button button = new Button(caption);
        button.setWidth(100, Unit.PERCENTAGE);
        button.setData(fragment);
        button.addClickListener(menuButtonClickListener);

        return button;
    }
}

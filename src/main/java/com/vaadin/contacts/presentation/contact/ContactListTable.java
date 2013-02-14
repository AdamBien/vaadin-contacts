package com.vaadin.contacts.presentation.contact;

import javax.inject.Inject;

import com.vaadin.contacts.business.contactstore.entity.Contact;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ContactListTable extends CustomComponent {

    private static final long serialVersionUID = -7480183573283968676L;
    @Inject
    ContactPresenter presenter;
    private Table table;
    private Button.ClickListener addContactListener = new Button.ClickListener() {
        private static final long serialVersionUID = -6696133062819506323L;

        @Override
        public void buttonClick(ClickEvent event) {
            presenter.onContactCreated();
        }
    };
    private Button.ClickListener removeContactListener = new Button.ClickListener() {
        private static final long serialVersionUID = 1972610062708878635L;

        @Override
        public void buttonClick(ClickEvent event) {
            presenter.onContactRemoved(null);
        }
    };
    private ValueChangeListener tableValueChangeListener = new ValueChangeListener() {
        private static final long serialVersionUID = -1395661949618344282L;

        @Override
        public void valueChange(ValueChangeEvent event) {
            presenter.onContactSelected(getSelectedContact());

            removeContactButton.setEnabled(getSelectedContact() != null);
        }
    };
    private Button removeContactButton;

    public ContactListTable() {
        setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        setCompositionRoot(layout);

        table = new Table();
        table.setSizeFull();
        table.setImmediate(true);
        table.setSelectable(true);
        table.addValueChangeListener(tableValueChangeListener);

        removeContactButton = new Button("Remove contact",
                removeContactListener);
        removeContactButton.setEnabled(false);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout
                .addComponent(new Button("Add contact", addContactListener));
        buttonLayout.addComponent(removeContactButton);

        layout.addComponent(table);
        layout.addComponent(buttonLayout);
        layout.setExpandRatio(table, 1);
    }

    @SuppressWarnings("unchecked")
    public BeanItem<Contact> getSelectedContact() {
        return (BeanItem<Contact>) table.getItem(table.getValue());
    }

    public void setContainerDatasource(BeanItemContainer<Contact> container) {
        table.setContainerDataSource(container);
    }
}

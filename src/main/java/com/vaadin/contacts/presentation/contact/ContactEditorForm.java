package com.vaadin.contacts.presentation.contact;

import javax.inject.Inject;

import com.vaadin.contacts.business.contactstore.entity.Contact;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ContactEditorForm extends CustomComponent {

    private static final long serialVersionUID = -3687729087771335040L;
    private FieldGroup fieldGroup;
    @PropertyId("firstName")
    private TextField firstName;
    @PropertyId("lastName")
    private TextField lastName;
    @PropertyId("city")
    private TextField city;
    @PropertyId("zipCode")
    private TextField zipCode;
    @PropertyId("comment")
    private TextArea comment;
    @Inject
    ContactPresenter presenter;
    private Button.ClickListener saveListener = new Button.ClickListener() {
        private static final long serialVersionUID = 6828226158238147870L;

        @Override
        public void buttonClick(ClickEvent event) {
            try {
                fieldGroup.commit();
                presenter.onContactSaved(contact);
            } catch (CommitException e) {
                e.printStackTrace();
            }
        }
    };
    private Button.ClickListener cancelListener = new Button.ClickListener() {
        private static final long serialVersionUID = 491742619788793193L;

        @Override
        public void buttonClick(ClickEvent event) {
            fieldGroup.discard();
        }
    };
    private Button save;
    private Contact contact;

    public ContactEditorForm() {
        setSizeFull();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();

        setCompositionRoot(layout);

        fieldGroup = new FieldGroup();
        fieldGroup.setBuffered(true);

        FormLayout formLayout = new FormLayout();
        formLayout.setSizeFull();

        firstName = new TextField("First name");
        firstName.setNullRepresentation("");

        lastName = new TextField("Last name");
        lastName.setNullRepresentation("");

        city = new TextField("City");
        city.setNullRepresentation("");

        zipCode = new TextField("Zip code");
        zipCode.setNullRepresentation("");

        comment = new TextArea("Comment");
        comment.setNullRepresentation("");

        layout.addComponent(firstName);
        layout.addComponent(lastName);
        layout.addComponent(city);
        layout.addComponent(zipCode);
        layout.addComponent(comment);

        HorizontalLayout buttonLayout = new HorizontalLayout();

        save = new Button("Save", saveListener);

        buttonLayout.addComponent(save);
        buttonLayout.addComponent(new Button("Cancel", cancelListener));

        layout.addComponent(formLayout);
        layout.addComponent(buttonLayout);

        layout.setExpandRatio(formLayout, 1);
        layout.setSpacing(true);

        setEnabled(false);
    }

    public void populateContact(BeanItem<Contact> contactItem) {
        if (contactItem == null) {
            clear();
            return;
        }

        setEnabled(contactItem != null);

        this.contact = contactItem.getBean();

        fieldGroup.setItemDataSource(contactItem);
        fieldGroup.bindMemberFields(this);
    }

    public void clear() {
        setEnabled(false);

        firstName.setValue(null);
        lastName.setValue(null);
        zipCode.setValue(null);
        city.setValue(null);
        comment.setValue(null);
    }
}

package com.vaadin.contacts.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;

public abstract class AbstractView<P extends AbstractPresenter<?>> extends
		CustomComponent implements View {

	private static final long serialVersionUID = 5180728810039011729L;

	private P presenter;

	protected void setPresenter(P presenter) {
		this.presenter = presenter;
	}

	protected P getPresenter() {
		return presenter;
	}
}

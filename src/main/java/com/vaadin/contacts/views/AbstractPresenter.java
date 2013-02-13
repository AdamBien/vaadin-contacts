package com.vaadin.contacts.views;

import com.vaadin.navigator.View;

public abstract class AbstractPresenter<V extends View> {

	private V view;

	public abstract void viewInitialized(V view);

	public abstract void viewEntered();

	protected void setView(V view) {
		this.view = view;
	}

	protected V getView() {
		return view;
	}
}

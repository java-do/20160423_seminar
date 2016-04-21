package com.example.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	private IModel<Double> valueModel;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		valueModel = Model.of(0.0);

		queue(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		queue(new Form<Void>("postToCalculationPage") {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				setResponsePage(new CalculationPage(valueModel));
			}
		});

		queue(new RequiredTextField<>("valueField", valueModel));


//		add(new Link<Void>("gotoCalculationPage") {
//			@Override
//			public void onClick() {
//				setResponsePage(new CalculationPage());
//			}
//		});

	}
}

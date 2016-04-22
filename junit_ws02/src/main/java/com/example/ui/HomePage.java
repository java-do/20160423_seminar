package com.example.ui;

import com.example.data.SquareLogBean;
import com.example.service.ICalculationService;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.time.LocalDateTime;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private ICalculationService service;

	private IModel<Double> valueModel;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		valueModel = Model.of(0.0);

		queue(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		queue(new Form<Void>("postToCalculationPage") {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				Double value = valueModel.getObject();
				Double square = service.square(value);
				SquareLogBean bean = new SquareLogBean(value, square, LocalDateTime.now());
				setResponsePage(new CalculationPage(Model.of(bean)));
			}
		});

		queue(new TextField<>("value", valueModel));
	}
}

package com.example.ui;

import com.example.service.ICalculationService;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class CalculationPage extends WebPage {

	@Inject
	private ICalculationService calculationService;

	public CalculationPage(IModel<Double> valueModel) {
		add(new Label("value", valueModel));
		add(new Label("square", calculationService.square(valueModel.getObject())));
	}
}

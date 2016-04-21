package com.example.ui;

import com.example.data.SquareLogBean;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class CalculationPage extends WebPage {

	public CalculationPage(IModel<Double> valueModel, IModel<Double> squareModel) {
		queue(new Label("value", valueModel));
		queue(new Label("square", squareModel));
	}

	public CalculationPage(IModel<SquareLogBean> squareLogBeanModel) {
		setDefaultModel(CompoundPropertyModel.of(squareLogBeanModel));
		queue(new Label("value"));
		queue(new Label("value"));
	}
}

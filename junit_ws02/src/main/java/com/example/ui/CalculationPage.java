package com.example.ui;

import com.example.service.ICalculationService;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class CalculationPage extends WebPage {

	@Inject
	private ICalculationService calculationService;

	public CalculationPage() {
		 add(new Label("test", calculationService.add(0,0)));
	}
}

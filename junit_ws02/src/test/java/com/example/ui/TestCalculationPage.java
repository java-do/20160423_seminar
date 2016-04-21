package com.example.ui;

import com.example.WicketApplication;
import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.google.inject.Module;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestCalculationPage {
	private WicketTester tester;
	private IModel<Double> valueModel;
	private IModel<Double> squareModel;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}


	@Test
	public void ページが表示される() {
		valueModel = Model.of(5.0);
		squareModel = Model.of(25.0);

		tester.startPage(new CalculationPage(valueModel, squareModel));

		tester.assertRenderedPage(CalculationPage.class);
	}

	@Test
	public void 引数の値がLabelに表示される() {
		valueModel = Model.of(2.2);
		squareModel = Model.of(4.84);

		tester.startPage(new CalculationPage(valueModel, squareModel));

		tester.assertLabel("value", "2.2");
		tester.assertLabel("square", "4.84");
	}

	@Test
	public void 引数の値が丸めてLabelに表示される() {
		valueModel = Model.of(5.0);
		squareModel = Model.of(25.0);

		tester.startPage(new CalculationPage(valueModel, squareModel));

		tester.assertLabel("value", "5");
		tester.assertLabel("square", "25");
	}
}

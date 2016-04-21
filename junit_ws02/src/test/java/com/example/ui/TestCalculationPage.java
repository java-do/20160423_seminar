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

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication() {
			@Override
			protected Module getGuiceModule() {
				return binder -> {
					binder.bind(ICalculationService.class).to(CalculationService.class);
				};
			}
		});
	}


	@Test
	public void ページが表示される() {
		valueModel = Model.of(5.0);

		tester.startPage(new CalculationPage(valueModel));

		tester.assertRenderedPage(CalculationPage.class);
	}

	@Test
	public void 引数の2乗が表示されている() {
		valueModel = Model.of(5.0);

		tester.startPage(new CalculationPage(valueModel));

		tester.assertLabel("square", "25");
	}
}

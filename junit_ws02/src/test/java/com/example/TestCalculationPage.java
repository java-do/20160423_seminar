package com.example;

import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.example.ui.CalculationPage;
import com.example.ui.HomePage;
import com.google.inject.Module;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestCalculationPage {
	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication(){
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
		tester.startPage(CalculationPage.class);
		tester.assertRenderedPage(CalculationPage.class);
	}
}

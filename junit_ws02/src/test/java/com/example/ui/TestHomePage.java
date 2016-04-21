package com.example.ui;

import com.example.WicketApplication;
import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.google.inject.Module;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
	private WicketTester tester;

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
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}
}

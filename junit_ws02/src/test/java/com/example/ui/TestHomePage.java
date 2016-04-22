package com.example.ui;

import com.example.WicketApplication;
import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.google.inject.Module;
import org.apache.wicket.util.tester.FormTester;
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
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void ページが表示される() {
		tester.startPage(HomePage.class);

		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void ボタンを押すと次ページに移動する() {
		tester.startPage(HomePage.class);

		FormTester sut = tester.newFormTester("postToCalculationPage");
		sut.setValue("value", "5.0");
		sut.submit();

		tester.assertRenderedPage(CalculationPage.class);

	}
}

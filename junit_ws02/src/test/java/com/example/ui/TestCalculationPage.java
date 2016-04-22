package com.example.ui;

import com.example.WicketApplication;
import com.example.data.SquareLogBean;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestCalculationPage {
	private WicketTester tester;
	private Clock clock;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
		Instant instant = Instant.parse("2016-04-23T13:00:00Z");
		clock = Clock.fixed(instant, ZoneId.systemDefault());
	}

	@Test
	public void ページが表示される() {
		SquareLogBean bean = new SquareLogBean(5.0, 25.0, LocalDateTime.now(clock));
		IModel<SquareLogBean> model = Model.of(bean);

		tester.startPage(new CalculationPage(model));

		tester.assertRenderedPage(CalculationPage.class);
	}

	@Test
	public void 引数の値がLabelに表示される() {
		SquareLogBean bean = new SquareLogBean(2.2, 4.84, LocalDateTime.now(clock));
		IModel<SquareLogBean> model = Model.of(bean);

		tester.startPage(new CalculationPage(model));

		tester.assertLabel("value", "2.2");
		tester.assertLabel("square", "4.84");
	}

	@Test
	public void 引数の値が丸めてLabelに表示される() {
		SquareLogBean bean = new SquareLogBean(5.0, 25.0, LocalDateTime.now(clock));
		IModel<SquareLogBean> model = Model.of(bean);

		tester.startPage(new CalculationPage(model));

		tester.assertLabel("value", "5");
		tester.assertLabel("square", "25");
	}
}

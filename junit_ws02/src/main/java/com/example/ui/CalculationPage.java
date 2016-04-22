package com.example.ui;

import com.example.data.SquareLogBean;
import com.example.service.ICalculationService;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

public class CalculationPage extends WebPage {

	@Inject
	private ICalculationService calculationService;

	public CalculationPage(IModel<SquareLogBean> squareLogBeanModel) {
		setDefaultModel(CompoundPropertyModel.of(squareLogBeanModel));
		queue(new Label("value"));
		queue(new Label("square"));

		IModel<List<SquareLogBean>> beansModel = Model.ofList(calculationService.fetchLogsLimit5());
		queue(new PropertyListView<SquareLogBean>("logs", beansModel) {

			@Override
			protected void populateItem(ListItem<SquareLogBean> item) {
				item.queue(new Label("timestamp"));
				item.queue(new Label("value"));
				item.queue(new Label("square"));
			}
		});

		queue(homePageLink("gotoHomePage"));
	}
}

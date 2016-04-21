package com.example;

import com.example.service.CalculationService;
import com.example.service.ICalculationService;
import com.example.ui.HomePage;
import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.crypt.CharEncoding;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see com.example.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
		getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
		initGuice();
	}

	protected void initGuice() {
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, getGuiceModule()));
	}

	protected Module getGuiceModule() {
		return binder -> {};
	}

}

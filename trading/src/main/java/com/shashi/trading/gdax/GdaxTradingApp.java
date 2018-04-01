package com.shashi.trading.gdax;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shashi.config.ApplicationConfigGdax;

public class GdaxTradingApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigGdax.class);
		SimpleWebSocketConnectionManager app = context.getBean(SimpleWebSocketConnectionManager.class);
		app.startApp();
	}
}

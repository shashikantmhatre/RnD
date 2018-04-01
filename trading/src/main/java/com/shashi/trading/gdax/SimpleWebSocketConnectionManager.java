package com.shashi.trading.gdax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.jetty.JettyWebSocketClient;

@Component
public class SimpleWebSocketConnectionManager {

	private static final String WS_URI = "wss://ws-feed.gdax.com";
	
	@Autowired
	JettyWebSocketClient webSocketClient;
	
	@Autowired
	SimpleClientWebSocketHandler handler;
	
	public void startApp() {
		WebSocketConnectionManager manager = new WebSocketConnectionManager(webSocketClient, handler, WS_URI);
		manager.start();
	}
	
}

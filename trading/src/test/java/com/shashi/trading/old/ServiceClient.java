package com.shashi.trading.old;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Component
public class ServiceClient {

	private static Logger logger = Logger.getLogger(ServiceClient.class);
	
	public void read() throws InterruptedException, ExecutionException, IOException {
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

		String url = "wss://ws-feed.gdax.com";
		StompSessionHandler sessionHandler = new MySessionHandler();
		ListenableFuture<StompSession> connect = stompClient.connect(url, sessionHandler);
		
		logger.info("connection aquired " + connect);
		
		StompSession stompSession = connect.get();
			
		logger.info("after aquired " + connect);

		Object msg = "{ \"type\": \"subscribe\" , \"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ], \"channels\": [ { \"name\": \"ticker\",\"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ] } ] }";
		
		
	}

}

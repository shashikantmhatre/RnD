package com.shashi.trading.old;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class MySessionHandler implements StompSessionHandler {

	private static Logger logger = Logger.getLogger(MySessionHandler.class);

	
	public Type getPayloadType(StompHeaders arg0) {
		System.out.println(arg0.toString());
		return null;
	}

	
	
	public void handleFrame(StompHeaders arg0, Object arg1) {
		System.out.println(arg1.toString());
	}

	public void afterConnected(StompSession arg0, StompHeaders arg1) {
		logger.info("Connected");
	}

	public void handleException(StompSession arg0, StompCommand arg1, StompHeaders arg2, byte[] arg3, Throwable arg4) {
		System.out.println("exception");
	}

	public void handleTransportError(StompSession arg0, Throwable arg1) {
		System.out.println("exception");

	}

}

package com.shashi.trading.old;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashi.config.ApplicationConfigGdax;
import com.shashi.trading.vo.MessageData;

/**
 * Hello world!
 *
 */
@WebSocket
// @Component
public class TradingData {

	@Autowired
	@Qualifier("mySessionFactory")
	SessionFactory sessionFactory;

	private static final Logger LOG = Log.getLogger(TradingData.class);

	public void start() {

		String url = "wss://ws-feed.gdax.com";

		SslContextFactory sslContextFactory = new SslContextFactory();
		sslContextFactory.setTrustAll(true); // The magic

		WebSocketClient client = new WebSocketClient(sslContextFactory);
		try {
			client.start();
			TradingData socket = new TradingData();
			Future<Session> fut = client.connect(socket, URI.create(url));
			Session session = fut.get();
			session.getRemote().sendString(
					"{ \"type\": \"subscribe\" , \"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ], \"channels\": [ { \"name\": \"ticker\",\"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ] } ] }");
		} catch (Throwable t) {
			LOG.warn(t);
		}
	}

	public static void main(String[] args) {
		String url = "wss://ws-feed.gdax.com";

		SslContextFactory sslContextFactory = new SslContextFactory();
		sslContextFactory.setTrustAll(true); // The magic

		WebSocketClient client = new WebSocketClient(sslContextFactory);
		try {
			client.start();
			TradingData socket = new TradingData();
			Future<Session> fut = client.connect(socket, URI.create(url));
			Session session = fut.get();
			session.getRemote().sendString(
					"{ \"type\": \"subscribe\" , \"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ], \"channels\": [ { \"name\": \"ticker\",\"product_ids\": [ \"BTC-USD\", \"LTC-USD\" ] } ] }");
		} catch (Throwable t) {
			LOG.warn(t);
		}

	}

	@OnWebSocketConnect
	public void onConnect(Session sess) {
		LOG.info("onConnect({})", sess);
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		LOG.info("onClose({}, {})", statusCode, reason);
	}

	@OnWebSocketError
	public void onError(Throwable cause) {
		LOG.warn(cause);
	}

	@OnWebSocketMessage
	public void onMessage(String msg) throws JsonParseException, JsonMappingException, IOException {


		ObjectMapper mapper = new ObjectMapper();
		MessageData data = mapper.readValue(msg, MessageData.class);
		LOG.info("onMessage() - {}", msg);
		System.out.println(data.getProductId() + " : " + data.getOpen24h() + " : " + data.getPrice() + " : "
				+ data.getLow24h() + " : " + data.getHigh24h());

		BigDecimal openingPrice = data.getOpen24h();
		BigDecimal currentPrice = data.getPrice();

		BigDecimal spread = new BigDecimal("0.3");
		if ((openingPrice != null || currentPrice != null)) {
			if (currentPrice.subtract(openingPrice).compareTo(spread) > -1) {
				System.out.println("Price is going down with "
						+ currentPrice.divide(openingPrice, 2, RoundingMode.HALF_UP) + " down ");
			} else {
				System.out.println("Current " + currentPrice.divide(openingPrice, 2, RoundingMode.HALF_UP));
			}
		}


	}
}

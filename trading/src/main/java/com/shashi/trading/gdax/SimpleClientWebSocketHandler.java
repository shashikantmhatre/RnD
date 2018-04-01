package com.shashi.trading.gdax;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.shashi.trading.vo.MessageData;
import com.shashi.trading.vo.TrendData;

@Component
public class SimpleClientWebSocketHandler extends AbstractWebSocketHandler {

	private static final AtomicInteger sequence = new AtomicInteger();

	private static final String TRADE_HISTORY_INSERT = "INSERT INTO TRADE_HISTORY (PRODUCT, ORDERSEQUENCE, CURRENTPRICE, OPEN24H, VOLUMN24H, LOW24H, HIGH24H, BESTBID, BESTASK, BIDTYPE, BIDTIME, RECEIVEDTIME, RECEIVEDORDERSEQUENCE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String ORDER_BOOK_MERGE = "MERGE INTO ORDER_BOOK (PRODUCT, SIDE, PRICE, SIZE) KEY ( PRODUCT, SIDE , PRICE ) VALUES ( ?, ?, ?, ? )";

	private static final String TICKER = "ticker";

	private static final String SNAPSHOT = "snapshot";

	private static final String L2UPDATE = "l2update";

	protected Logger logger = Logger.getLogger(SimpleClientWebSocketHandler.class);

	@Autowired
	DataSource datasource;

	@Autowired
	TrendObserver trendObserver;
	
	Connection connection; 

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		connection = datasource.getConnection();
		
		logger.info("afterConnectionEstablished: ");
		session.setTextMessageSizeLimit(500000);
		
		TextMessage message = new TextMessage(IOUtils.toByteArray(
				new FileInputStream(new File("src\\main\\java\\com\\shashi\\trading\\gdax\\subsribe.txt"))));
		session.sendMessage(message);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
//		System.out.println(message.getPayload().toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new ISO8601DateFormat());
		MessageData messageData = mapper.readValue(message.getPayload().toString(), MessageData.class);

//		System.out.println("message " + messageData);
		if (TICKER.equals(messageData.getType()) && messageData.getSide() != null) {
//			 trendObserver.update(messageData);
			 saveTradeHistory(messageData);
		} else if (SNAPSHOT.equals(messageData.getType())) {
//			saveBids(messageData.getProductId(), messageData.getBids());
//			saveAsks(messageData.getProductId(), messageData.getAsks());
		} else if (L2UPDATE.equals(messageData.getType())) {
			saveChanges(messageData.getProductId(), messageData.getChanges());
		}
	}

	private void saveChanges(String product, List<List<String>> changes) throws SQLException {
//		Connection connection = datasource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(ORDER_BOOK_MERGE);
		try {
			changes.forEach(change -> {
				try {
					prepareStatement.setString(1, product);
					prepareStatement.setString(2, change.get(0).toUpperCase());
					prepareStatement.setBigDecimal(3, new BigDecimal(change.get(1)));
					prepareStatement.setBigDecimal(4, new BigDecimal(change.get(2)));
					
					prepareStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(e);
				}
			});
		} finally {
			prepareStatement.close();
//			connection.close();
		}
	}

	private void saveBids(String product, List<List<BigDecimal>> bids) throws SQLException {
//		Connection connection = datasource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(ORDER_BOOK_MERGE);
		try {
			bids.forEach(bid -> {
				try {
					prepareStatement.setString(1, product);
					prepareStatement.setString(2, "BUY");
					prepareStatement.setBigDecimal(3, bid.get(0));
					prepareStatement.setBigDecimal(4, bid.get(1));
					
					prepareStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(e);
				}
			});
		} finally {
			prepareStatement.close();
//			connection.close();
		}
	}

	
	
	
	private void saveAsks(String product, List<List<BigDecimal>> asks) throws SQLException {
//		Connection connection = datasource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(ORDER_BOOK_MERGE);
		try {
			asks.forEach(ask -> {
				try {
					prepareStatement.setString(1, product);
					prepareStatement.setString(2, "SELL");
					prepareStatement.setBigDecimal(3, ask.get(0));
					prepareStatement.setBigDecimal(4, ask.get(1));
					
					prepareStatement.executeUpdate();
				} catch (SQLException e) {
					logger.error(e);
				}
			});
		} finally {
			prepareStatement.close();
//			connection.close();
		}
	}

	private void saveTradeHistory(MessageData ticker) throws SQLException {
//		Connection connection = datasource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(TRADE_HISTORY_INSERT);
		try {
			prepareStatement.setString(1, ticker.getProductId());
			prepareStatement.setBigDecimal(2, ticker.getSequence());
			prepareStatement.setBigDecimal(3, ticker.getPrice());
			prepareStatement.setBigDecimal(4, ticker.getOpen24h());
			prepareStatement.setBigDecimal(5, ticker.getLastSize());
			prepareStatement.setBigDecimal(6, ticker.getLow24h());
			prepareStatement.setBigDecimal(7, ticker.getHigh24h());
			prepareStatement.setBigDecimal(8, ticker.getBestBid());
			prepareStatement.setBigDecimal(9, ticker.getBestAsk());
			prepareStatement.setString(10, ticker.getSide());
			prepareStatement.setTimestamp(11, new Timestamp(ticker.getBidTime().getTimeInMillis()));
			prepareStatement.setTimestamp(12, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			prepareStatement.setInt(13, sequence.getAndIncrement());

			prepareStatement.executeUpdate();
		} finally {
			prepareStatement.close();
//			connection.close();
		}
	}

}

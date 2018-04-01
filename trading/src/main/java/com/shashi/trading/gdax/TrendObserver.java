package com.shashi.trading.gdax;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shashi.trading.vo.MessageData;
import com.shashi.trading.vo.TrendData;

@Component
public class TrendObserver {

	private static final String THRESOLD_UPPER_LIMIT = "0.10";
	private static final String THRESOLD_LOWER_LIMIT = "-0.10";
	private static final String BUY = "BUY";
	private static final String SELL = "SELL";
	private static final String TRADE_TREND_INSERT = "INSERT INTO TRADE_TREND (PRODUCT, NEWPRICE, OPEN24H, VOLUMN24H, LOW24H, HIGH24H, BESTBID, BESTASK, TRENDINGTOWARDS, TRENDCHANGETIME, OLDPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private Map<String, TrendData> trendDataMap = new ConcurrentHashMap<>();

	@Autowired
	DataSource datasource;
	
	protected Logger logger = Logger.getLogger(TrendObserver.class);


	public TrendData update(MessageData ticker) {
		TrendData trendData = addIfNotExist(ticker);
		updateTrendData(trendData, ticker);
		return trendData;
	}

	private TrendData updateTrendData(TrendData trendData, MessageData ticker) {

		if (trendData.getProductId() == null) {
			trendData.setProductId(ticker.getProductId());
		}

		if (trendData.getOpen24h() == null) {
			trendData.setOpen24h(ticker.getOpen24h());
		}

		// low statistics
		if (trendData.getLow24h() == null) {
			trendData.setLow24h(ticker.getLow24h());
			// updateTredingTowards(trendData, ticker.getOpen24h(), trendData.getLow24h());
		} else {
			if (trendData.getLow24h().compareTo(ticker.getLow24h()) > 0) {
				// if this happens, means people are selling their coins
				// updateTredingTowards(trendData, trendData.getLow24h(), ticker.getLow24h());
				trendData.setLow24h(ticker.getLow24h());
			}
		}

		// high statistics
		if (trendData.getHigh24h() == null) {
			trendData.setHigh24h(ticker.getHigh24h());
			// updateTredingTowards(trendData, ticker.getOpen24h(), trendData.getHigh24h());
		} else {
			if (trendData.getHigh24h().compareTo(ticker.getHigh24h()) < 0) {
				// if this happens, means people are buying more coins
				// updateTredingTowards(trendData, trendData.getHigh24h(), ticker.getHigh24h());
				trendData.setHigh24h(ticker.getHigh24h());
			}
		}

		trendData.setBestAsk(ticker.getBestAsk());
		trendData.setBestBid(ticker.getBestBid());

		// trend statistics
		if (trendData.getPrice() == null) {
			trendData.setPrice(ticker.getPrice());
			saveTradeTrend(trendData);
		} else {
			if (isChangeAboveSpecifiedThreashold(trendData, ticker)) {
				// if this happens, means people are buying or selling more coins, lets find out
				// a trend
				updateTredingTowards(trendData, trendData.getPrice(), ticker.getPrice());
				trendData.setOldPrice(trendData.getPrice());
				trendData.setPrice(ticker.getPrice());
				saveTradeTrend(trendData);
			}

		}

		return trendData;
	}

	private boolean isChangeAboveSpecifiedThreashold(TrendData trendData, MessageData ticker) {
		BigDecimal difference = trendData.getPrice().subtract(ticker.getPrice());
		BigDecimal highThreashold = new BigDecimal(THRESOLD_UPPER_LIMIT);
		BigDecimal lowThreashold = new BigDecimal(THRESOLD_LOWER_LIMIT);
		return trendData.getPrice().compareTo(ticker.getPrice()) != 0
				&& (difference.compareTo(highThreashold) > 0 || difference.compareTo(lowThreashold) < 0) ? true : false;
	}

	private void updateTredingTowards(TrendData trendData, BigDecimal pastLow, BigDecimal currentLow) {
		if (pastLow.compareTo(currentLow) > 0) {
			trendData.setTrendingTowards(SELL);
		} else if (pastLow.compareTo(currentLow) < 0) {
			trendData.setTrendingTowards(BUY);
		}
	}

	private TrendData addIfNotExist(MessageData ticker) {
		if (!trendDataMap.containsKey(ticker.getProductId())) {
			TrendData trendData = new TrendData();
			trendDataMap.put(ticker.getProductId(), trendData);
			return trendData;
		} else {
			return trendDataMap.get(ticker.getProductId());
		}
	}

	private void saveTradeTrend(TrendData trendData) {
		Connection connection;
		try {
			connection = datasource.getConnection();

			PreparedStatement prepareStatement = connection.prepareStatement(TRADE_TREND_INSERT);
			try {
				prepareStatement.setString(1, trendData.getProductId());
				prepareStatement.setBigDecimal(2, trendData.getPrice());
				prepareStatement.setBigDecimal(3, trendData.getOpen24h());
				prepareStatement.setBigDecimal(4, new BigDecimal("0"));
				prepareStatement.setBigDecimal(5, trendData.getLow24h());
				prepareStatement.setBigDecimal(6, trendData.getHigh24h());
				prepareStatement.setBigDecimal(7, trendData.getBestBid());
				prepareStatement.setBigDecimal(8, trendData.getBestAsk());
				prepareStatement.setString(9, trendData.getTrendingTowards());
				prepareStatement.setTimestamp(10, new Timestamp(Calendar.getInstance().getTimeInMillis()));
				prepareStatement.setBigDecimal(11, trendData.getOldPrice());
				prepareStatement.executeUpdate();
			} finally {
				prepareStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			logger.error(e);
		}

	}

}

package com.shashi.triggers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.api.Trigger;

public class LogTradeInit implements Trigger {

	@Override
	public void close() throws SQLException {
	}

	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

		BigDecimal newSize = (BigDecimal) newRow[3];

		System.out.println("insert 1 record for " + newRow[0].toString() + " and "
				+ newRow[1].toString() + " and " + newRow[2].toString());
		
		// insert into the trade request for the new request
		try (PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO TRADE_REQUEST (PRODUCT, SIDE, PRICE, REQUEST_SIZE, TRADE_TIME, REQUEST_TYPE)\r\n"
						+ "VALUES(?, ?, ?, ?, SYSDATE,?)")) {
			stmt.setString(1, newRow[0].toString());
			stmt.setString(2, newRow[1].toString());
			stmt.setBigDecimal(3, (BigDecimal) newRow[2]);
			stmt.setBigDecimal(4, newSize);
			stmt.setString(5, "I");
			stmt.executeUpdate();
		}
	}

	@Override
	public void init(Connection arg0, String arg1, String arg2, String arg3, boolean arg4, int arg5)
			throws SQLException {

	}

	@Override
	public void remove() throws SQLException {

	}

}

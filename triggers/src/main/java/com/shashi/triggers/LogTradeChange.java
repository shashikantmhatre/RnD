package com.shashi.triggers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.api.Trigger;

public class LogTradeChange implements Trigger {

	@Override
	public void close() throws SQLException {
	}

	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {

		BigDecimal newSize = (BigDecimal) newRow[3];
		BigDecimal oldSize = (BigDecimal) oldRow[3];

		BigDecimal changeValue = newSize.subtract(oldSize);

		if (newSize.equals(BigDecimal.ZERO)) {
			// delete all the records from Trade_request for the current product, side
			try (PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM TRADE_REQUEST WHERE PRODUCT = ? AND SIDE = ? AND PRICE = ?")) {
				stmt.setString(1, oldRow[0].toString());
				stmt.setString(2, oldRow[1].toString());
				stmt.setBigDecimal(3, (BigDecimal) newRow[2]);
				int executeUpdate = stmt.executeUpdate();
				System.out.println("deleted " + executeUpdate + " records for " + oldRow[0].toString() + " and "
						+ oldRow[1].toString() + " and " + newRow[2].toString());
			}
		} else {

			if (changeValue.signum() < 0) {
				// delete the value from trade request as this size is removed from side
				try (PreparedStatement stmt = conn
						.prepareStatement("DELETE FROM TRADE_REQUEST WHERE PRODUCT = ? AND SIDE = ? AND PRICE = ? AND REQUEST_SIZE = ?")) {
					stmt.setString(1, oldRow[0].toString());
					stmt.setString(2, oldRow[1].toString());
					stmt.setBigDecimal(3, (BigDecimal) newRow[2]);
					stmt.setBigDecimal(4, changeValue.negate());
					stmt.executeUpdate();
				}
			} else {
				// insert into the trade request for the new request
				try (PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO TRADE_REQUEST (PRODUCT, SIDE, PRICE, REQUEST_SIZE, TRADE_TIME)\r\n"
								+ "VALUES(?, ?, ?, ?, SYSDATE)")) {
					stmt.setString(1, oldRow[0].toString());
					stmt.setString(2, oldRow[1].toString());
					stmt.setBigDecimal(3, (BigDecimal) newRow[2]);
					stmt.setBigDecimal(4, changeValue);
					stmt.executeUpdate();
				}
			}
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

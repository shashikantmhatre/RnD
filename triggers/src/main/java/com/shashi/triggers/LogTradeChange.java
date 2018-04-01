package com.shashi.triggers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.api.Trigger;

public class LogTradeChange implements Trigger {

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
		 try (PreparedStatement stmt = conn.prepareStatement(
		            "INSERT INTO TRADE_REQUEST\r\n" + 
		            "(PRODUCT, SIDE, PRICE, REQUEST_SIZE, TRADE_TIME)\r\n" + 
		            "VALUES(?, ?, ?, ?, SYSDATE)")
		        ) {
			 
			 		BigDecimal changeValue = ((BigDecimal)newRow[3]).subtract((BigDecimal)oldRow[3]); 
			 
		            stmt.setString(1, oldRow[0].toString());
		            stmt.setString(2, oldRow[1].toString());
		            stmt.setBigDecimal(3, (BigDecimal)newRow[2]);
		            stmt.setBigDecimal(4, changeValue);
		            stmt.executeUpdate();
		        }
	}

	@Override
	public void init(Connection arg0, String arg1, String arg2, String arg3, boolean arg4, int arg5)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() throws SQLException {
		// TODO Auto-generated method stub

	}

}

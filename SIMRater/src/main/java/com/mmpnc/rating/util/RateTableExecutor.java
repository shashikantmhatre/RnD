package com.mmpnc.rating.util;

//import org.hibernate.Session;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mmpnc.context.Context;
import com.mmpnc.context.ContextParam;
import com.mmpnc.rating.dao.BaseDAO;
import com.mmpnc.rating.dao.RatingDAOFactory;
//import com.mmpnc.rating.dao.impl.hibernate.ObjectDAO;
//import com.mmpnc.rating.data.dao.impl.hibernate.RateTableDAO;
//import com.mmpnc.rating.data.dao.impl.hibernate.RateTableEntryDAO;
import com.mmpnc.rating.vo.Product;
import com.mmpnc.rating.vo.RateTable;
import com.mmpnc.rating.vo.RateTableConfiguration;
import com.mmpnc.rating.vo.RateTableEntry;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Oct 12, 2013
 *
 * Description :
 *
 * Note :
 */

public class RateTableExecutor implements TableExecutor {
	private Product product;
	private RateTable rt;
	private RateTableEntry entry;
	private RateTableConfiguration rtConfig;
	private Context context;
	private Object session;
	private Object value;
	private String valueType;
	private static final Logger logger = LoggerFactory
			.getLogger(RateTableExecutor.class);

	public RateTableExecutor(Context contex) {
		this.context = contex;
		this.session = this.context.getValue(ContextParam.SESSION);
	}

	@Override
	public RateTableExecutor execute(String rateTableName, Object[] values) {
		
		if (logger.isInfoEnabled()) {
			logger.info("************** RateTable [ " + rateTableName +" ] Execution *************" );
			logger.info("Execution Start Time , " + Calendar.getInstance().getTimeInMillis());
			System.out.print("RateTable [ " + rateTableName +" ] Execution Time , " + Calendar.getInstance().getTimeInMillis() + " , ");
		}
		
		
		this.product = (Product) context.getValue(ContextParam.RATEBOOK);

		if (logger.isInfoEnabled()) {
			logger.info("Ratebook ID -> " + product.getId());
		}
		this.rt = new RateTable();
		this.rt.setName(rateTableName);
		this.rt.setProductId(product);

//		ObjectDAO bd = new RateTableDAO(this.rt);
		BaseDAO bd = RatingDAOFactory.getRatingDAOFactory().getRateTableDAO(this.rt);
	
		this.rt = bd.getUniqueRecord(this.session);

		if (logger.isInfoEnabled()) {
			logger.info("RateTable ID -> " + this.rt.getId());
		}

		if (this.rt == null) {
			logger.error("Could not find Ratetable " + rateTableName);
			return this;
		}

		for(RateTableConfiguration config : this.rt.getRateTableConfiguration()){
			if(config.getConfigurationType().equals("VALUE")){
				this.rtConfig = config;
			}
		}
		
//		bd = new RateTableEntryDAO(this.rt, values);
		bd =  RatingDAOFactory.getRatingDAOFactory().getRateTableEntryDAO(this.rt, values);
	
		this.entry = bd.getUniqueRecord(session);

		if (this.entry != null) {
			
			if (getVarType(this.rtConfig.getType()).equals("String")) {
				this.value = this.entry.getReturnValue();
				this.valueType = getVarType(this.rtConfig.getType());
				
				if(logger.isInfoEnabled()){
					logger.info("We got the String Entry value as " + this.value);
				}
				
			} else {
				this.value = this.entry.getReturnNValue();
				this.valueType = getVarType(this.rtConfig.getType());
				
				if(logger.isInfoEnabled()){
					logger.info("We got the Numeric Entry value as " + this.value);
				}
			}
		} else {
			
			if(logger.isInfoEnabled()){
				logger.info("We did not get a return value from RateTableEntry");
			}
			
			this.value = new Double(0);
			this.valueType = "BigDecimal";
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("Execution End Time , " + Calendar.getInstance().getTimeInMillis());
			System.out.println(Calendar.getInstance().getTimeInMillis());
		}
		
		return this;
	}

	@Override
	public String getValueType() {
		return valueType;
	}

	@Override
	public Object getValue() {
		return value;
	}

	private String getVarType(String variableType) {
		VarType vartype = VarType.valueOf(variableType);
		switch (vartype) {
		case decimal:
		case integer:
			return "BigDecimal";
		case nvarchar:
			return "String";
		default:
			return "BigDecimal";
		}
	}

	private enum VarType {
		decimal, nvarchar, integer;
	}
}
